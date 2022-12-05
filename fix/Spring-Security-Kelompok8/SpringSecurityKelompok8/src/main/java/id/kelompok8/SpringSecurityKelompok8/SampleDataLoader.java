/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8;

import com.github.javafaker.Faker;
import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
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
    private final UserEntityRepository userEntityRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        // create 5 rows of table role fake data
//        List<Role> roles = IntStream.rangeClosed(1,5)
//                .mapToObj(i -> new Role(0,
//                        "ROLES_"+faker.job().field().toUpperCase(),
//                        null)
//                ).collect(Collectors.toList());
        if (roleRepository.findByName("ROLE_USER").isPresent()) {
            System.out.println("ROLE_USER data already exist.");
        } else {
            List<Role> roles = new ArrayList<>();
            roles.add(new Role(0, "ROLE_USER", null));
            roles.add(new Role(0, "ROLE_ADMIN", null));
            roleRepository.saveAll(roles);
//         create 5 rows of table role fake data
        List<UserEntity> userEntity = IntStream.rangeClosed(1,5)
                .mapToObj(u -> new UserEntity(0, 
                        faker.name().username(), 
                        faker.bothify("?#?#?#"), 
                        false, 
                        faker.bothify("?#?#?#?#?#?#"),
                        0,
                        Arrays.asList(roleRepository.findByName("ROLE_USER").get())
                
                )
                ).collect(Collectors.toList());

        userEntityRepository.saveAll(userEntity);
        }
    }
}
