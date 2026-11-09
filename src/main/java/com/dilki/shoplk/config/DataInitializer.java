package com.dilki.shoplk.config;

import com.dilki.shoplk.entity.Role;
import com.dilki.shoplk.entity.User;
import com.dilki.shoplk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@shoplk.com")) {
            User admin = User.builder()
                    .email("admin@shoplk.com")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("Shop Admin")
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("✅ Admin created: admin@shoplk.com / admin123");
        }
    }
}