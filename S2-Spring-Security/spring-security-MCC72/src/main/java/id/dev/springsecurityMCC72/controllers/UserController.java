package id.dev.springsecurityMCC72.controllers;

import id.dev.springsecurityMCC72.models.User;
import id.dev.springsecurityMCC72.services.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("user")
//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {

  private UserService userService;

//  @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
  @GetMapping
  public String home() {
    return "Welcome to USER Home Page";
  }

//  @PreAuthorize("hasAuthority('READ_USER')")
  @GetMapping("dashboard")
  public String dashboard() {
    return "<p style=\"color:blue;\">Welcome to USER Dashboard</p>";
  }

//  @PreAuthorize("hasAuthority('READ_ADMIN')")
  @GetMapping("landing-page")
  public String landingPage() {
    return "<p style=\"color:blue;\">Welcome to USER Landing Page</p>";
  }

//  @PreAuthorize("hasAnyAuthority('READ_USER','READ_ADMIN')")
  @GetMapping("v2")
  public List<User> getAll() {
    return userService.getAll();
  }

//  @PreAuthorize("hasAuthority('READ_ADMIN')")
  @GetMapping("/v2/{id}")
  public User getById(@PathVariable Integer id) {
    return userService.getById(id);
  }

//  @PreAuthorize("hasAuthority('CREATE_ADMIN')")
  @PostMapping("/v2")
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

//  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  @DeleteMapping("/v2/{id}")
  public User delete(@PathVariable Integer id) {
    return userService.delete(id);
  }
}
