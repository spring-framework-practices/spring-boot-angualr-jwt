package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.enumeration.Authority;
import com.trl.springbootspringsecurityangularjwt.enumeration.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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
                () -> {assertEquals(id, userDto.getId(), "The getter that returns the user's 'id' does not work correctly");},
                () -> {assertEquals(userId, userDto.getUserId(), "The getter that returns the user's 'userId' does not work correctly");},
                () -> {assertEquals(firstName, userDto.getFirstName(), "The getter that returns the user's 'firstName' does not work correctly");},
                () -> {assertEquals(lastName, userDto.getLastName(), "The getter that returns the user's 'lastName' does not work correctly");},
                () -> {assertEquals(username, userDto.getUsername(), "The getter that returns the user's 'username' does not work correctly");},
                () -> {assertEquals(password, userDto.getPassword(), "The getter that returns the user's 'password' does not work correctly");},
                () -> {assertEquals(email, userDto.getEmail(), "The getter that returns the user's 'email' does not work correctly");},
                () -> {assertEquals(profileImageUrl, userDto.getProfileImageUrl(), "The getter that returns the user's 'profileImageUrl' does not work correctly");},
                () -> {assertEquals(lastLoginDate, userDto.getLastLoginDate(), "The getter that returns the user's 'lastLoginDate' does not work correctly");},
                () -> {assertEquals(lastLoginDateDisplay, userDto.getLastLoginDateDisplay(), "The getter that returns the user's 'lastLoginDateDisplay' does not work correctly");},
                () -> {assertEquals(joinDate, userDto.getJoinDate(), "The getter that returns the user's 'joinDate' does not work correctly");},
                () -> {assertEquals(role, userDto.getRole(), "The getter that returns the user's 'role' does not work correctly");},
                () -> {assertEquals(authorities, userDto.getAuthorities(), "The getter that returns the user's 'authorities' does not work correctly");},
                () -> {assertEquals(isActive, userDto.isActive(), "The getter that returns the user's 'isActive' does not work correctly");},
                () -> {assertEquals(isNotLocked, userDto.isNotLocked(), "The getter that returns the user's 'isNotLocked' does not work correctly");}
        );
    }

    @Test
    public void testGettersFail() {
        assertAll("Testing Getters Methods.",
                () -> {assertNotEquals(id + 1, userDto.getId());},
                () -> {assertNotEquals(userId + "XXX", userDto.getUserId());},
                () -> {assertNotEquals(firstName + "XXX", userDto.getFirstName());},
                () -> {assertNotEquals(lastName + "XXX", userDto.getLastName());},
                () -> {assertNotEquals(username + "XXX", userDto.getUsername());},
                () -> {assertNotEquals(password + "XXX", userDto.getPassword());},
                () -> {assertNotEquals(email + "XXX", userDto.getEmail());},
                () -> {assertNotEquals(profileImageUrl + "XXX", userDto.getProfileImageUrl());},
                () -> {assertNotEquals(lastLoginDate.plusSeconds(1), userDto.getLastLoginDate());},
                () -> {assertNotEquals(lastLoginDateDisplay.plusSeconds(1), userDto.getLastLoginDateDisplay());},
                () -> {assertNotEquals(joinDate.plusSeconds(1), userDto.getJoinDate());},
                () -> {assertNotEquals(Role.ROLE_USER, userDto.getRole());},
                () -> {assertNotEquals(Authority.USER_AUTHORITIES, userDto.getAuthorities());},
                () -> {assertNotEquals(!isActive, userDto.isActive());},
                () -> {assertNotEquals(!isNotLocked, userDto.isNotLocked());}
        );
    }

    @Test
    public void testSetters() {

    }

    @Test
    public void testSettersFail() {

    }

}