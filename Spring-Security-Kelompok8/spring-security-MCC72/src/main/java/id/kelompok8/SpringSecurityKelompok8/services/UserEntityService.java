/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.repositories.RoleRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserEntityRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author DevidBa
 */
@Service
@AllArgsConstructor
public class UserEntityService implements UserDetailsService {
    
    private BCryptPasswordEncoder passwordEncoder;
    private UserEntityRepository ur;
    private RoleRepository rr;
    private MailContentBuilder mailContentBuilder;
    private JavaMailSender mailSender;
    public static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCK_TIME_DURATION = 24*60*60*1000;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = ur.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!!!")
                );
        if(!user.getIsActive()) throw new UsernameNotFoundException("User not activated!!!");
        
        UserDetails ud = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities());
        return ud;
    }
    
    public List<GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
     public List<Map<String, Object>> getAllMap() {

        return ur.findAll().stream().map(role -> {
            Map<String, Object> m = new HashMap<>();
            m.put("userId", role.getId());
            m.put("userName", role.getUsername());
            m.put("userVerificationCode", role.getVerificationCode());
            m.put("userActivationStatus", role.getIsActive());
            m.put("userRoles", role.getUserRole());
            return m;
        }).collect(Collectors.toList());
    }
     
     public Map<String, Object> getLoginResponse() {
 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return auth.getName();
       
            Map<String, Object> m = new HashMap<>();
            m.put("userName", auth.getName());
            m.put("userRoles", auth.getAuthorities().toString());
            
            return m;
    }
     
    public UserEntity insert(UserRegistrationDto u){
        System.out.println("service here");
        String verificationCode = UUID.randomUUID().toString();
        UserEntity ue = new UserEntity();
                ue.setUsername(u.getUsername());
                ue.setIsActive(false);
                ue.setVerificationCode(verificationCode);
                ue.setPassword(passwordEncoder.encode(u.getPassword()));
                ue.setUserRole(Arrays.asList(rr.findByName("ROLE_USER").get()));

        return ur.save(ue);
    }
    
      public void sendVerifyMail(UserRegistrationDto userEntity) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
            messageHelper.setTo("hndhryd@gmail.com");
            messageHelper.setSubject("Verification Mail");
            String content = mailContentBuilder.build(userEntity.getUsername());
            messageHelper.setText(content, true);
            messageHelper.addInline("image-1", new ClassPathResource("static/images/image-1.png"), "image/png");
            messageHelper.addInline("image-2", new ClassPathResource("static/images/image-2.png"), "image/png");

        };

        mailSender.send(messagePreparator);
        System.out.println("Send email with verify link...");

//        return "Verification mail sent.";
    }

    
    public Boolean verify(String username, String token){ // parameter ganti tipe user
    //ambil token dari database where username
    String tokenDB = ur.findByUsername(username).get().getVerificationCode();
    int id = ur.findByUsername(username).get().getId();
    UserEntity user = ur.findByUsername(username).get();
    user.setIsActive(true);
    if(token.equalsIgnoreCase(tokenDB)){
        ur.save(user);
        return true;
    } else
        return false;
    
    }
    
    public void increaseFailedAttempts(UserEntity user){
        int newFailAttempts = user.getFailedAttempt() + 1;
        ur.updateFailedAttempts(newFailAttempts, user.getUsername());
    }
    
    public void resetFailedAttempts(String username){
        ur.updateFailedAttempts(0, username);
    }
    
    public void lock(UserEntity user){
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        
        ur.save(user);
    }
    
    public boolean unlockWhenTimeExpired(UserEntity user){
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
        
        if(lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis){
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            ur.save(user);
            return true;
        }
        return false;
    }
    
    
}
