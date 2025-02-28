package com.scm.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repsitories.UserRepo;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepo userRepo;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserServiceImpl userService;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setUserId(1L);
    user.setEmail("test@example.com");
    user.setPassword("password123");
    user.setName("Test User");
  }

  @Test
  void testSaveUser() {
    // when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
    when(userRepo.save(any(User.class))).thenReturn(user);

    User savedUser = userService.saveUser(user);

    assertNotNull(savedUser);
    assertEquals("Test User", savedUser.getName());
    verify(userRepo, times(1)).save(any(User.class));
  }

  @Test
  void testGetUserById_Success() {
    when(userRepo.findById(1L)).thenReturn(Optional.of(user));

    Optional<User> foundUser = userService.getUserById(1L);

    assertTrue(foundUser.isPresent());
    assertEquals("test@example.com", foundUser.get().getEmail());
    verify(userRepo, times(1)).findById(1L);
  }

  @Test
  void testGetUserById_NotFound() {
    when(userRepo.findById(99L)).thenReturn(Optional.empty());

    Optional<User> result = userService.getUserById(99L);

    assertFalse(result.isPresent());
    verify(userRepo, times(1)).findById(99L);
  }

  @Test
  void testUpdateUser_Success() {
    when(userRepo.findById(1L)).thenReturn(Optional.of(user));
    when(userRepo.save(any(User.class))).thenReturn(user);

    user.setName("Updated Name");

    Optional<User> updatedUser = userService.updateUser(user);

    assertTrue(updatedUser.isPresent());
    assertEquals("Updated Name", updatedUser.get().getName());
    verify(userRepo, times(1)).save(any(User.class));
  }

  @Test
  void testUpdateUser_NotFound() {
    // Ensure the user ID matches what we're stubbing
    user.setUserId(99L);

    when(userRepo.findById(99L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(user));

    assertTrue(exception.getMessage().contains("User not found"));
    verify(userRepo, times(1)).findById(99L);
  }

  @Test
  void testDeleteUser_Success() {
    when(userRepo.findById(1L)).thenReturn(Optional.of(user));
    doNothing().when(userRepo).delete(user);

    userService.deleteUser(1L);

    verify(userRepo, times(1)).delete(user);
  }

  @Test
  void testDeleteUser_NotFound() {
    when(userRepo.findById(99L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(99L));

    assertTrue(exception.getMessage().contains("User not found"));
    verify(userRepo, never()).delete(any(User.class));
  }

  @Test
  void testIsUserExist_Success() {
    when(userRepo.findById(1L)).thenReturn(Optional.of(user));

    boolean exists = userService.isUserExist(1L);

    assertTrue(exists);
    verify(userRepo, times(1)).findById(1L);
  }

  @Test
  void testIsUserExist_Failure() {
    when(userRepo.findById(99L)).thenReturn(Optional.empty());

    boolean exists = userService.isUserExist(99L);

    assertFalse(exists);
    verify(userRepo, times(1)).findById(99L);
  }

  @Test
  void testIsUserExistByEmail_Success() {
    when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

    boolean exists = userService.isUserExistByEmail("test@example.com");

    assertTrue(exists);
    verify(userRepo, times(1)).findByEmail("test@example.com");
  }

  @Test
  void testIsUserExistByEmail_Failure() {
    when(userRepo.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

    boolean exists = userService.isUserExistByEmail("unknown@example.com");

    assertFalse(exists);
    verify(userRepo, times(1)).findByEmail("unknown@example.com");
  }

  @Test
  void testGetAllUsers() {
    when(userRepo.findAll()).thenReturn(List.of(user));

    List<User> users = userService.getAllUsers();

    assertEquals(1, users.size());
    assertEquals("Test User", users.get(0).getName());
    verify(userRepo, times(1)).findAll();
  }

  @Test
  void testGetUserByEmail_Success() {
    when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

    User foundUser = userService.getUserByEmail("test@example.com");

    assertNotNull(foundUser);
    assertEquals("test@example.com", foundUser.getEmail());
    verify(userRepo, times(1)).findByEmail("test@example.com");
  }

  @Test
  void testGetUserByEmail_NotFound() {
    when(userRepo.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

    User foundUser = userService.getUserByEmail("unknown@example.com");

    assertNull(foundUser);
    verify(userRepo, times(1)).findByEmail("unknown@example.com");
  }

  @Test
  void testLoadUserByUsername_Success() {
    when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

    UserDetails userDetails = userService.loadUserByUsername("test@example.com");

    assertNotNull(userDetails);
    assertEquals("test@example.com", userDetails.getUsername());
    verify(userRepo, times(1)).findByEmail("test@example.com");
  }

  @Test
  void testLoadUserByUsername_NotFound() {
    when(userRepo.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

    Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
      userService.loadUserByUsername("unknown@example.com");
    });

    assertTrue(exception.getMessage().contains("User not found"));
    verify(userRepo, times(1)).findByEmail("unknown@example.com");
  }
}
