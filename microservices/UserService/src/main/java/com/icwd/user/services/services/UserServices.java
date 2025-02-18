package com.icwd.user.services.services;

import java.util.List;

import com.icwd.user.services.entities.User;

public interface UserServices {
  User saveUser(User user);

  List<User> getAllUsers();

  User getUserById(String userId);

  void deleteUser(String userId);

  User updateUser(String userId, User updatedUser);
}
