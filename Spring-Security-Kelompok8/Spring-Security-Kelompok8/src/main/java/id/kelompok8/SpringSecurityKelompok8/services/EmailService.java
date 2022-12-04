package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.EmailRequest;
import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;
    private UserRepository userRepository;

    public void sendVerifyMail(String username) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
            messageHelper.setTo("arssein@gmail.com");
            messageHelper.setSubject("Verification Mail");
            String content = mailContentBuilder.build(username);
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
    String tokenDB = userRepository.findByUsername(username).get().getVerificationCode();
    int id = userRepository.findByUsername(username).get().getId();
    UserEntity user = userRepository.findByUsername(username).get();
    user.setIsActive(true);
    if(token.equalsIgnoreCase(tokenDB)){
        userRepository.save(user);
        return true;
    } else
        return false;
    
    }

}
