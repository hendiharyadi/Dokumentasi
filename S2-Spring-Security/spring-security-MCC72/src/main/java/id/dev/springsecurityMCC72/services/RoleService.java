package id.dev.springsecurityMCC72.services;

import id.dev.springsecurityMCC72.models.Role;
import id.dev.springsecurityMCC72.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RoleService {

  private RoleRepository roleRepository;

  public Role getById(Integer id) {
    return roleRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!!")
      );
  }
}
