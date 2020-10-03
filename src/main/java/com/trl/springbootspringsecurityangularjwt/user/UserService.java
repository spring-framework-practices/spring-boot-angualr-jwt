package com.trl.springbootspringsecurityangularjwt.user;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

    UserDto register(String firstName, String lastName, String username, String email) throws MessagingException;

    List<UserDto> getUsers();

    UserDto findUserByUsername(String username);

    UserDto findUserByEmail(String email);
}
