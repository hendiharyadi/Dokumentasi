/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.claintappsecurity.controller;

import lombok.AllArgsConstructor;
import mii.claintappsecurity.model.dto.LoginRequest;
import mii.claintappsecurity.service.LoginService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Hendi
 */
@Controller
@AllArgsConstructor
public class AuthController {

    private LoginService loginService;

    @GetMapping("/login")
    public String loginView(LoginRequest loginRequest, Authentication auth) {
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(LoginRequest loginRequest) {
        if (!loginService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        return "dashboard";
    }
   
}