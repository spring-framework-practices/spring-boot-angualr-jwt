package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.exception.domain.*;
import com.trl.springbootspringsecurityangularjwt.service.EmailService;
import com.trl.springbootspringsecurityangularjwt.service.LoginAttemptService;

import static com.trl.springbootspringsecurityangularjwt.constant.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static com.trl.springbootspringsecurityangularjwt.constant.UserImplConstant.*;
import static com.trl.springbootspringsecurityangularjwt.constant.UserImplLoggerHeaderConstant.*;
import static com.trl.springbootspringsecurityangularjwt.enumeration.Authority.USER_AUTHORITIES;
import static com.trl.springbootspringsecurityangularjwt.enumeration.Role.ROLE_USER;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }

    @Transactional
    @Override
    public UserDto register(String firstName, String lastName, String username, String email) {
        UserDto userResult = null;
        LOGGER.debug(REGISTER_LOGGER_HEADER + "firstName = " + firstName + " lastName = " + lastName + " username = " + username + " email = " + email);
        if (isBlank(firstName) || isBlank(lastName) || isBlank(username) || isBlank(email)) {
            LOGGER.error(REGISTER_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }

        validateNewUsernameForMatchWithExistingUsername(username);
        validateEmail(email);
        validateNewEmailForMatchWithExistingEmail(email);

        String password = generatePassword();

        UserDto userDto = new UserDto.Builder()
                .withUserId(generateUserId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withUsername(username)
                .withPassword(encodePassword(password))
                .withEmail(email)
                .withProfileImageUrl(getTemporaryProfileImageUrl(username))
                .withJoinDate(LocalDateTime.now())
                .withRole(ROLE_USER)
                .withAuthorities(USER_AUTHORITIES)
                .isActive(true)
                .isNotLocked(true)
                .build();
        UserEntity savedUser = userRepository.save(userConverter.mapDtoToEntity(userDto));
        userResult = userConverter.mapEntityToDto(savedUser);
        LOGGER.debug(REGISTER_LOGGER_HEADER + "registered user = " + userResult);
        try {
            emailService.sendNewPasswordEmail(firstName, password, email);
        } catch (MessagingException e) {
            LOGGER.error(REGISTER_LOGGER_HEADER + e.getMessage());
            e.printStackTrace();
        }
        return userResult;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userResults = null;
        // TODO: This mithod is dangerous with over data. Need to do pagination.
        List<UserEntity> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            LOGGER.error(GET_USERS_LOGGER_HEADER + NO_USERS_FOUND);
            throw new UsersNotFoundException(NO_USERS_FOUND);
        }
        userResults = userConverter.mapListEntitiesToListDto(allUsers);
        LOGGER.debug(GET_USERS_LOGGER_HEADER + "userResult = " + userResults);
        return userResults;
    }

    @Override
    public UserDto findUserByUsername(String username) {
        UserDto userResult = null;
        LOGGER.debug(FIND_USER_BY_USERNAME_LOGGER_HEADER + "username = " + username);
        if (isBlank(username)) {
            LOGGER.error(FIND_USER_BY_USERNAME_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserEntity userByUsername = userRepository.findUserByUsername(username);
        if (userByUsername == null) {
            LOGGER.error(FIND_USER_BY_USERNAME_LOGGER_HEADER + format(NO_USER_FOUND_BY_USERNAME, username));
            throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME);
        }
        userResult = userConverter.mapEntityToDto(userByUsername);
        LOGGER.debug(FIND_USER_BY_USERNAME_LOGGER_HEADER + "userResult = " + userResult);
        return userResult;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        UserDto userResult = null;
        LOGGER.debug(FIND_USER_BY_EMAIL_LOGGER_HEADER + "email = " + email);
        if (isBlank(email)) {
            LOGGER.error(FIND_USER_BY_EMAIL_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserEntity userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail == null) {
            LOGGER.error(FIND_USER_BY_EMAIL_LOGGER_HEADER + format(NO_USER_FOUND_BY_EMAIL, email));
            throw new UserNotFoundException(format(NO_USER_FOUND_BY_EMAIL, email));
        }
        userResult = userConverter.mapEntityToDto(userByEmail);
        LOGGER.debug(FIND_USER_BY_EMAIL_LOGGER_HEADER + "userResult = " + userResult);
        return userResult;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetailsResult = null;
        LOGGER.debug(LOAD_USER_BY_USERNAME_LOGGER_HEADER + "username = " + username);
        if (isBlank(username)) {
            LOGGER.error(LOAD_USER_BY_USERNAME_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserDto user = userConverter.mapEntityToDto(userRepository.findUserByUsername(username));
        if (user == null) {
            LOGGER.error(LOAD_USER_BY_USERNAME_LOGGER_HEADER + format(NO_USER_FOUND_BY_USERNAME, username));
            throw new UsernameNotFoundException(format(NO_USER_FOUND_BY_USERNAME, username));
        } else {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(userConverter.mapDtoToEntity(user));
            userDetailsResult = new UserPrincipal(user);
            LOGGER.debug(LOAD_USER_BY_USERNAME_LOGGER_HEADER + "userDetailsResult = " + userDetailsResult);
            return userDetailsResult;
        }
    }

    private void validateNewUsernameForMatchWithExistingUsername(String username) {
        LOGGER.debug(VALIDATE_NEW_USERNAME_FOR_MATCHING_WITH_EXISTING_USERNAME_LOGGER_HEADER + "username = " + username);
        if (isBlank(username)) {
            LOGGER.error(VALIDATE_NEW_USERNAME_FOR_MATCHING_WITH_EXISTING_USERNAME_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserEntity userByNewUsername = userRepository.findUserByUsername(username);
        if (userByNewUsername != null) {
            LOGGER.error(VALIDATE_NEW_USERNAME_FOR_MATCHING_WITH_EXISTING_USERNAME_LOGGER_HEADER + format(USERNAME_ALREADY_EXISTS, username));
            throw new UsernameExistException(format(USERNAME_ALREADY_EXISTS, username));
        }
        LOGGER.debug(VALIDATE_NEW_EMAIL_FOR_MATCHING_WITH_EXISTING_EMAIL_LOGGER_HEADER + "username = " + username + " this username is not taken.");
    }

    private void validateEmail(String email) {
        LOGGER.debug(VALIDATE_EMAIL_LOGGER_HEADER + "email = " + email);
        if (isBlank(email)) {
            LOGGER.error(VALIDATE_EMAIL_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            LOGGER.error(VALIDATE_EMAIL_LOGGER_HEADER + format(EMAIL_ADDRESS_INVALID, email));
            throw new EmailIsInvalidException(format(EMAIL_ADDRESS_INVALID, email));
        }
        LOGGER.debug(VALIDATE_EMAIL_LOGGER_HEADER + "email = " + email + " is valid.");
    }

    private void validateNewEmailForMatchWithExistingEmail(String email) {
        LOGGER.debug(VALIDATE_NEW_EMAIL_FOR_MATCHING_WITH_EXISTING_EMAIL_LOGGER_HEADER + "email = " + email);
        if (isBlank(email)) {
            LOGGER.error(VALIDATE_NEW_EMAIL_FOR_MATCHING_WITH_EXISTING_EMAIL_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserEntity userByNewEmail = userRepository.findUserByEmail(email);
        if (userByNewEmail != null) {
            LOGGER.error(VALIDATE_NEW_EMAIL_FOR_MATCHING_WITH_EXISTING_EMAIL_LOGGER_HEADER + format(EMAIL_ALREADY_EXISTS, email));
            throw new EmailExistException(format(EMAIL_ALREADY_EXISTS, email));
        }
        LOGGER.debug(VALIDATE_NEW_EMAIL_FOR_MATCHING_WITH_EXISTING_EMAIL_LOGGER_HEADER + "email = " + email + " this email is not taken.");
    }

    private String encodePassword(String password) {
        String encodedPasswordResult = null;
        // TODO: Security vulnerability.
        LOGGER.debug(ENCODE_PASSWORD_LOGGER_HEADER + "password = " + password);
        if (isBlank(password)) {
            LOGGER.error(ENCODE_PASSWORD_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        encodedPasswordResult = passwordEncoder.encode(password);
        // TODO: Security vulnerability.
        LOGGER.debug(ENCODE_PASSWORD_LOGGER_HEADER + "encode password = " + encodedPasswordResult);
        return encodedPasswordResult;
    }

    private String generatePassword() {
        String passwordResult = null;
        passwordResult = RandomStringUtils.randomAlphanumeric(10);
        // TODO: Security vulnerability.
        LOGGER.debug(GENERATE_PASSWORD_LOGGER_HEADER + "password = " + passwordResult);
        return passwordResult;
    }

    private String generateUserId() {
        String userIdResult = null;
        userIdResult = RandomStringUtils.randomNumeric(10);
        LOGGER.debug(GENERATE_USER_ID_LOGGER_HEADER + "user id = " + userIdResult);
        return userIdResult;
    }

    private String getTemporaryProfileImageUrl(String username) {
        String urlResult = null;
        LOGGER.debug(GET_TEMPORARY_PROFILE_IMAGE_URL_LOGGER_HEADER + "username = " + username);
        if (isBlank(username)) {
            LOGGER.error(ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        urlResult = ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
        LOGGER.debug(GET_TEMPORARY_PROFILE_IMAGE_URL_LOGGER_HEADER + "url = " + urlResult);
        return urlResult;
    }

    private void validateLoginAttempt(UserDto user) {
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
