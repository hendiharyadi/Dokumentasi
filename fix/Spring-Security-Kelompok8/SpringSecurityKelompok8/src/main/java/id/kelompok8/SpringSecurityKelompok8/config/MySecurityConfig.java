/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.config;


import com.github.javafaker.Faker;
import id.kelompok8.SpringSecurityKelompok8.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author DevidBa
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserEntityService userDetailsService;
    
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
      .csrf()
      .disable()
      .cors()
      .disable()
      .authorizeRequests()
      .anyRequest()
      .permitAll()
      .and()
      .httpBasic();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/role***").permitAll()
//                .antMatchers("/user***").permitAll()
////                .antMatchers("/user/*").hasAnyRole("ADMIN","USER")
//                .antMatchers("/activatedUser*").hasAnyRole("USER")
////                .antMatchers("/**").authenticated()
////                .antMatchers("/landing-page").permitAll()
////                .and()
////                .formLogin()
//                .and()
//                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider);
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
