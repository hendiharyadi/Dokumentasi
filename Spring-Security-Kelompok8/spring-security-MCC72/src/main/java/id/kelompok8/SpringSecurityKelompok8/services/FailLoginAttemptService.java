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
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hendi
 */
@Component
public class FailLoginAttemptService extends SimpleUrlAuthenticationFailureHandler{
    
    private MyUserDetails myUserDetails;
    private UserEntity userEntity;
    private UserEntityService userEntityService;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
      String username = request.getParameter("username");
      User u = myUserDetails.getByUsername(username);
      
        
        if (u != null){
            if(u.isEnabled()&&u.isAccountNonLocked()){
                if(userEntity.getFailedAttempt() < userEntityService.MAX_FAILED_ATTEMPTS - 1){
                    userEntityService.increaseFailedAttempts(userEntity);
                } else{
                    userEntityService.lock(userEntity);
                    exception = new LockedException("Your account has been locked and will be unlocked after 24 hours.");
                }
            } else if (userEntity.isAccountNonLocked()){
                if(userEntityService.unlockWhenTimeExpired(userEntity)){
                    exception = new LockedException("Your account has been set to free!");
                }
            }
        }
        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
