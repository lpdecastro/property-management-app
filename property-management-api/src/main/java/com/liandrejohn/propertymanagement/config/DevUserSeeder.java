package com.liandrejohn.propertymanagement.config;

import com.liandrejohn.propertymanagement.user.User;
import com.liandrejohn.propertymanagement.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevUserSeeder {

    @Bean
    CommandLineRunner seedUser(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               @Value("${app.dev-user.password}") String password) {
        return args -> {
            String email = "admin@example.com";

            if (!userRepository.existsByEmail(email)) {
                userRepository.save(new User("Admin", email,
                        passwordEncoder.encode(password)));
            }
        };
    }
}
