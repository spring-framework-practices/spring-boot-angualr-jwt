package com.trl.springbootspringsecurityangularjwt.tmp;

import com.trl.springbootspringsecurityangularjwt.user.UserEntity;
import com.trl.springbootspringsecurityangularjwt.user.UserRepository;

import static com.trl.springbootspringsecurityangularjwt.enumeration.Authority.SUPER_ADMIN_AUTHORITIES;
import static com.trl.springbootspringsecurityangularjwt.enumeration.Role.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        UserEntity entity = new UserEntity.Builder()
                .withUserId("0123456789")
                .withFirstName("Tsyupryk")
                .withLastName("Roman")
                .withUsername("TRL")
                .withPassword(passwordEncoder.encode("0123456789"))
                .withEmail("tsyupryk.roman@gmail.com")
                .withProfileImageUrl("someURL")
                .withJoinDate(LocalDateTime.now())
                .withRole(ROLE_SUPER_ADMIN)
                .withAuthorities(SUPER_ADMIN_AUTHORITIES)
                .isActive(true)
                .isNotLocked(true)
                .build();

        userRepository.save(entity);
    }
}
