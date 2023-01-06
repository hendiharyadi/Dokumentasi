package id.dev.springsecurityMCC72.services;

import id.dev.springsecurityMCC72.models.Role;
import id.dev.springsecurityMCC72.models.User;
import id.dev.springsecurityMCC72.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private RoleService roleService;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!")
      );
  }

  public User create(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    List<Role> role = new ArrayList<>();
    role.add(roleService.getById(2));
    user.setRoles(role);

    return userRepository.save(user);
  }

  public User delete(Integer id){
    User user = getById(id);
    userRepository.delete(user);
    return user;
  }
}
