/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.config;


import id.kelompok8.SpringSecurityKelompok8.services.FailLoginAttemptService;
import id.kelompok8.SpringSecurityKelompok8.services.SuccessLoginAttemptService;
import id.kelompok8.SpringSecurityKelompok8.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author DevidBa
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserEntityService userDetailsService;
    
    @Autowired
    private FailLoginAttemptService flas;
    
    @Autowired
    private SuccessLoginAttemptService slas;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/role/*").permitAll()
                .antMatchers("/user/*").permitAll()
//                .antMatchers("/user/*").hasAnyRole("ADMIN","USER")
                .antMatchers("/activatedUser*").hasAnyRole("USER")
//                .antMatchers("/**").authenticated()
//                .antMatchers("/landing-page").permitAll()
                .and()
                .formLogin()
//                    .loginPage("/login")
                    .usernameParameter("username")
                    .failureHandler(flas)
                    .successHandler(slas)
                    .permitAll()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .inMemoryAuthentication()
//                .withUser("admin123").password("123").roles("ADMIN")
//                .and()
//                .withUser("user123").password("123").roles("USER")
//                .and()
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
    
    
}
