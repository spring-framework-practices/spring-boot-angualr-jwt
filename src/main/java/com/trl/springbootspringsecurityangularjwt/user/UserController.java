package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.constant.SecurityConstant;
import com.trl.springbootspringsecurityangularjwt.utility.JwtTokenProvider;

import static com.trl.springbootspringsecurityangularjwt.constant.SecurityConstant.JWT_TOKEN_HEADER;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) {
        authenticate(user.getUsername(), user.getPassword());
        UserDto loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> register(@Validated @RequestBody UserDto user) {
        UserDto register = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return httpHeaders;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}

