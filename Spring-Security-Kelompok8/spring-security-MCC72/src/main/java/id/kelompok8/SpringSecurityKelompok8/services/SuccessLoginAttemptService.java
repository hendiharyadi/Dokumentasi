/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hendi
 */
@Component
public class SuccessLoginAttemptService extends SimpleUrlAuthenticationSuccessHandler{
    
    @Autowired
    private UserEntityService userEntityService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        CustomUserDetailsService userDetails = (CustomUserDetailsService) authentication.getPrincipal();
        UserEntity user = userDetails.getUser();
        if(user.getFailedAttempt()>0){
            userEntityService.resetFailedAttempts(user.getUsername());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
