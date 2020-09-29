package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.exception.domain.EmailExistException;
import com.trl.springbootspringsecurityangularjwt.exception.domain.EmailIsInvalidException;
import com.trl.springbootspringsecurityangularjwt.exception.domain.UserNotFoundException;
import com.trl.springbootspringsecurityangularjwt.exception.domain.UsernameExistException;

import static com.trl.springbootspringsecurityangularjwt.constant.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static com.trl.springbootspringsecurityangularjwt.constant.UserImplConstant.*;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(String firstName, String lastName, String username, String email) {

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

        LOGGER.info("New user password: " + password);

//        emailService.sendNewPasswordEmail(firstName, password, email);
        return userConverter.mapEntityToDto(savedUser);
    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error(format("User not found by username: %s", username));
            throw new UsernameNotFoundException("User not found by username: " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);

            UserDto userDto = userConverter.mapEntityToDto(user);
            UserPrincipal userPrincipal = new UserPrincipal(userDto);
            LOGGER.info(format("Returning found user by username: %s", username));

            return userPrincipal;
        }
    }

    private void validateNewUsernameForMatchWithExistingUsername(String newUsername) {
        UserDto userByNewUsername = findUserByUsername(newUsername);
        if (userByNewUsername != null) {
            LOGGER.error(format(USERNAME_ALREADY_EXISTS, newUsername));
            throw new UsernameExistException(format(USERNAME_ALREADY_EXISTS, newUsername));
        }
    }

    private void validateCurrentUsername(String currentUsername) {
        if (isBlank(currentUsername)) {
            LOGGER.error(ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserDto currentUser = findUserByUsername(currentUsername);
        if (currentUser == null) {
            LOGGER.error(format(NO_USER_FOUND_BY_USERNAME, currentUsername));
            throw new UserNotFoundException(format(NO_USER_FOUND_BY_USERNAME, currentUsername));
        }
    }

    private void validateEmail(String email) {
        if (isBlank(email)) {
            LOGGER.error(ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            LOGGER.error(format(EMAIL_ADDRESS_INVALID, email));
            throw new EmailIsInvalidException(format(EMAIL_ADDRESS_INVALID, email));
        }
    }

    private void validateNewEmailForMatchWithExistingEmail(String newEmail) {
        if (isBlank(newEmail)) {
            LOGGER.error(ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        UserDto userByNewEmail = findUserByEmail(newEmail);
        if (userByNewEmail != null) {
            LOGGER.error(format(EMAIL_ALREADY_EXISTS, newEmail));
            throw new EmailExistException(format(EMAIL_ALREADY_EXISTS, newEmail));
        }
    }

    private String encodePassword(String password) {
        if (isBlank(password)) {
            LOGGER.error(ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private String getTemporaryProfileImageUrl(String username) {
        if (isBlank(username)) {
            LOGGER.error(ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }
}
