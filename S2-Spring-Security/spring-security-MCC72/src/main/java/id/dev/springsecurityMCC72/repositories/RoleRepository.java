package id.dev.springsecurityMCC72.repositories;

import id.dev.springsecurityMCC72.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {}
