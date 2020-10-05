package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.enumeration.Authority;
import com.trl.springbootspringsecurityangularjwt.enumeration.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDtoTest {

    private UserDto userDto;

    private static Long id;
    private static String userId;
    private static String firstName;
    private static String lastName;
    private static String username;
    private static String password;
    private static String email;
    private static String profileImageUrl;
    private static LocalDateTime lastLoginDate;
    private static LocalDateTime lastLoginDateDisplay;
    private static LocalDateTime joinDate;
    private static Role role;
    private static Authority authorities;
    private static boolean isActive;
    private static boolean isNotLocked;


    @BeforeAll
    static void beforeAll() {
        id = 1L;
        userId = "0123456789";
        firstName = "Tsyupryk";
        lastName = "Roman";
        username = "TRL";
        password = "password";
        email = "tsyupryk.roman@gmail.com";
        profileImageUrl = "url";
        lastLoginDate = LocalDateTime.MIN;
        lastLoginDateDisplay = LocalDateTime.MIN;
        joinDate = LocalDateTime.MIN;
        role = Role.ROLE_SUPER_ADMIN;
        authorities = Authority.SUPER_ADMIN_AUTHORITIES;
        isActive = true;
        isNotLocked = true;
    }

    @BeforeEach
    void setUp() {
        userDto = new UserDto.Builder()
                .withId(id)
                .withUserId(userId)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withUsername(username)
                .withPassword(password)
                .withEmail(email)
                .withProfileImageUrl(profileImageUrl)
                .withLastLoginDate(lastLoginDate)
                .withLastLoginDateDisplay(lastLoginDateDisplay)
                .withJoinDate(joinDate)
                .withRole(role)
                .withAuthorities(authorities)
                .isActive(isActive)
                .isNotLocked(isNotLocked)
                .build();
    }

    @Test
    public void testGetters() {
        assertAll("Testing Getters Methods.",
                () -> {assertEquals(id, userDto.getId());},
                () -> {assertEquals(userId, userDto.getUserId());},
                () -> {assertEquals(firstName, userDto.getFirstName());},
                () -> {assertEquals(lastName, userDto.getLastName());},
                () -> {assertEquals(username, userDto.getUsername());},
                () -> {assertEquals(password, userDto.getPassword());},
                () -> {assertEquals(email, userDto.getEmail());},
                () -> {assertEquals(profileImageUrl, userDto.getProfileImageUrl());},
                () -> {assertEquals(lastLoginDate, userDto.getLastLoginDate());},
                () -> {assertEquals(lastLoginDateDisplay, userDto.getLastLoginDateDisplay());},
                () -> {assertEquals(joinDate, userDto.getJoinDate());},
                () -> {assertEquals(role, userDto.getRole());},
                () -> {assertEquals(authorities, userDto.getAuthorities());},
                () -> {assertEquals(isActive, userDto.isActive());},
                () -> {assertEquals(isNotLocked, userDto.isNotLocked());}
        );
    }

}