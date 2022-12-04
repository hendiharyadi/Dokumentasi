/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
     public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
     
       @Override
    public UserEntity save(UserRegistrationDto registrationDto) {
        String token = UUID.randomUUID().toString();
        
        UserEntity user = new UserEntity(0,
                registrationDto.getUsername(),
            passwordEncoder.encode(registrationDto.getPassword()), 
                false,
            token, 
            Arrays.asList(new Role("ROLE_USER"))
        );

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username).get();
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password is wrong.");
        }
        boolean enabled = !userEntity.getIsActive();
        UserDetails user = User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .disabled(enabled)
                .authorities(getAuthorities(userEntity.getRoles())).build();

        return user;
        
//        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), getAuthorities(userEntity.getRoles()));
    }

     private List<GrantedAuthority> getAuthorities(List <Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
     
    public List<UserEntity> getAll(){
        if (userRepository.findAll().isEmpty()){
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Data not found!!!");
        }
        return userRepository.findAll();
    }
    
    public UserEntity getById(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!!!"));
    }
    
    public UserEntity create(UserEntity user){
        return userRepository.save(user);
    }
    
    public UserEntity update(Integer id, UserEntity user){
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }
    
    public UserEntity delete(Integer id){
        UserEntity user = getById(id);
        userRepository.delete(user);
        return user;
    }
    
    public List<Map<String, Object>> active(){
        return userRepository.findAll().stream().map(user ->{
            Map<String, Object> u = new HashMap<>();
            u.put("username", user.getUsername());
            u.put("roles", user.getRoles());
            return u;
        }).collect((Collectors.toList()));
    }
}
