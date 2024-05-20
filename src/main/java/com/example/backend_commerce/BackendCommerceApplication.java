package com.example.backend_commerce;

import com.example.backend_commerce.models.Role;
import com.example.backend_commerce.models.User;
import com.example.backend_commerce.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
public class BackendCommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCommerceApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

//            userService.saveUser(new User(null, new HashSet<>(), "minhhieuvux", "123456", "minhhieuvux@gmail.com", "Minh", "Hieu", "02834423124", "Thai Binh", 4, "Thai Thuy", "Vietnam", "123456", "hfhff"));
//
//            userService.addRoleToUser("minhhieuvux@gmail.com", "ROLE_ADMIN");
//            userService.addRoleToUser("minhhieuvux@gmail.com", "ROLE_USER");
//            userService.addRoleToUser("minhhieuvux@gmail.com", "ROLE_MANAGER");
//            userService.addRoleToUser("minhhieuvux@gmail.com", "ROLE_SUPER_ADMIN");
        };
    }
}