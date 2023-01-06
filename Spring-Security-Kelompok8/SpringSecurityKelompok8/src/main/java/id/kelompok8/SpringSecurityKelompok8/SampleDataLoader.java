/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8;

import com.github.javafaker.Faker;
import id.kelompok8.SpringSecurityKelompok8.models.entity.Privilege;
import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.repositories.PrivilegeRepository;
import id.kelompok8.SpringSecurityKelompok8.repositories.RoleRepository;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserEntityRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author robby
 */
@AllArgsConstructor
@Component
public class SampleDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserEntityRepository userEntityRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.findByName("ROLE_USER").isPresent()) {
            System.out.println("ROLE_USER data already exist.");
        } else {
            List<Role> roles = new ArrayList<>();
            roles.add(new Role(0, "ROLE_USER", null, null));
            roles.add(new Role(0, "ROLE_ADMIN", null, null));
            roleRepository.saveAll(roles);
        }

        if (privilegeRepository.findByName("CREATE_USER").isPresent()) {
            System.out.println("CREATE_USER data already exist.");
        } else {
            List<Role> rolesUser = Arrays.asList(roleRepository.findByName("ROLE_USER").get());
            List<Role> rolesAdmin = Arrays.asList(roleRepository.findByName("ROLE_ADMIN").get());
            List<Privilege> privileges = new ArrayList<>();
            privileges.add(new Privilege(0, "CREATE_USER",rolesUser ));
            privileges.add(new Privilege(0, "READ_USER", rolesUser));
            privileges.add(new Privilege(0, "UPDATE_USER", rolesUser));
            privileges.add(new Privilege(0, "DELETE_USER", rolesUser));

            privileges.add(new Privilege(0, "CREATE_ADMIN", rolesAdmin));
            privileges.add(new Privilege(0, "READ_ADMIN", rolesAdmin));
            privileges.add(new Privilege(0, "UPDATE_ADMIN", rolesAdmin));
            privileges.add(new Privilege(0, "DELETE_ADMIN", rolesAdmin));

            privilegeRepository.saveAll(privileges);
        }
    }
}
