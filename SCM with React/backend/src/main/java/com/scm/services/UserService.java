package com.scm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.scm.entities.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> updateUser(User user);

    void deleteUser(Long id);

    boolean isUserExist(Long userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User processOAuthPostLogin(OAuth2User oAuth2User, String provider);

    // add more methods here related user service[logic]

}
