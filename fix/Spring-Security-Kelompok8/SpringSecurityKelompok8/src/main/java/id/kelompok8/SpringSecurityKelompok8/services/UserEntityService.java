/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.repositories.EmployeeRepository;
import id.kelompok8.SpringSecurityKelompok8.repositories.RoleRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserEntityRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private EmployeeRepository er;
    private MailContentBuilder mailContentBuilder;
    private JavaMailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = ur.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!!!")
                );
        if (!user.getIsActive()) {
            throw new UsernameNotFoundException("User not activated!!!");
        }
        //if attemp failed > 3 throw new error account locked
        if (user.getFailedAttempt() >= 3) {
            throw new UsernameNotFoundException("Account locked. Contact developer for more information.");
        }
        UserDetails ud = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities());
//        UserDetails ud = new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), getAuthorities(user.getUserRole()));
//reset attempt failed on db
// ur.setFailedAttemptForUser(0, user.getId());
        return ud;
    }

    public List<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
//    private List<GrantedAuthority> getAuthorities(List <Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }

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
        m.put("userId", ur.findByUsername(auth.getName()).get().getId());
        m.put("name", auth.getName());
        m.put("authorities", auth.getAuthorities().toString());

        return m;
    }

    public UserEntity insert(UserRegistrationDto u) {
        System.out.println("service here");
        System.out.println(u.getRoles());
        String verificationCode = UUID.randomUUID().toString();
        UserEntity ue = new UserEntity();
        ue.setUsername(u.getUsername());
        ue.setIsActive(false);
        ue.setVerificationCode(verificationCode);
        ue.setPassword(passwordEncoder.encode(u.getPassword()));
        ue.setUserRole(u.getRoles().stream().map(roleId -> rr.findById(roleId).get()).collect(Collectors.toList()));
        ue.setFailedAttempt(0);
        ue.setEmployee(er.findByEmail(u.getEmail()).get());
        return ur.save(ue);
//        return ue;
    }

    //update failed attempt +1
    public void updateAttempt(UserEntity u) {
        System.out.println("updateAttempt here");
//        String verificationCode = UUID.randomUUID().toString();
        UserEntity ue = ur.findByUsername(u.getUsername()).get();
//        ue.setUsername(u.getUsername());
//        ue.setIsActive(false);
//        ue.setVerificationCode(verificationCode);
//        ue.setPassword(passwordEncoder.encode(u.getPassword()));
//        ue.setUserRole(Arrays.asList(rr.findByName("ROLE_USER").get()));
//        ue.setFailedAttempt(0);
        ur.setFailedAttemptForUser(ue.getFailedAttempt() + 1, ue.getId());
        System.out.println("updateAttempt success");
    }

    public void sendVerifyMail(UserRegistrationDto userEntity) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
            messageHelper.setTo(userEntity.getEmail()); //change to userEntity.getEmail();
            messageHelper.setSubject("Verification Mail");
            String content = mailContentBuilder.build(userEntity.getUsername());
            messageHelper.setText(content, true);
            messageHelper.addInline("image-1", new ClassPathResource("static/images/image-1.png"), "image/png");
            messageHelper.addInline("image-2", new ClassPathResource("static/images/image-2.png"), "image/png");

        };

        mailSender.send(messagePreparator);
        System.out.println("Send email to "+userEntity.getEmail()+" with verify link...");

//        return "Verification mail sent.";
    }

    public Boolean verify(String username, String token) { // parameter ganti tipe user
        //ambil token dari database where username
        String tokenDB = ur.findByUsername(username).get().getVerificationCode();
        int id = ur.findByUsername(username).get().getId();
        UserEntity user = ur.findByUsername(username).get();
        user.setIsActive(true);
        if (token.equalsIgnoreCase(tokenDB)) {
            ur.save(user);
            return true;
        } else {
            return false;
        }

    }
    
//      public List<Map<String, Object>> getAllMapAuth(){
//      return ur.findAll().stream().map(auth -> {
//          Map<String, Object> m = new HashMap<>();
//          m.put("userId", auth.getId());
//          m.put("name", auth.getUsername());
//          m.put("authorities", getAllMapRole());
//          return m;
//      }).collect(Collectors.toList());
//  }
//  
//  public List<Map<String, Object>> getAllMapRole(){
//      return rr.findAll().stream().map(auth -> {
//          Map<String, Object> m = new HashMap<>();
//          m.put("role", auth.getName());
//          m.put("privilege", auth.getPrivileges());
//          return m;
//      }).collect(Collectors.toList());
//  }
}
