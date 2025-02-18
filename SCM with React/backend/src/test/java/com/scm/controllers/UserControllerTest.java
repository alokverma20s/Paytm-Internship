package com.scm.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.scm.entities.User;
import com.scm.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private User user;
  private UserDetails userDetails;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setUserId(1L);
    user.setEmail("test@example.com");
    user.setName("Test User");

    userDetails = mock(UserDetails.class);
    when(userDetails.getUsername()).thenReturn("test@example.com");
  }

  @Test
  void testGetUserProfile_Success() {
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);

    ResponseEntity<User> response = userController.getUserProfile(userDetails);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());

    // Add a check before accessing getBody()
    assertTrue(response.hasBody(), "Response should have a body");
    assertNotNull(response.getBody(), "Response body should not be null");

    assertEquals("test@example.com", response.getBody().getEmail());
    assertNull(response.getBody().getContacts()); // Ensure contacts are null

    verify(userService, times(1)).getUserByEmail("test@example.com");
  }

  @Test
  void testGetUserProfile_UserNotFound() {
    when(userService.getUserByEmail("unknown@example.com")).thenReturn(null);
    when(userDetails.getUsername()).thenReturn("unknown@example.com");

    ResponseEntity<User> response = userController.getUserProfile(userDetails);

    assertNotNull(response);
    assertEquals(404, response.getStatusCode().value());
    assertNull(response.getBody()); // Should return null if user is not found
    verify(userService, times(1)).getUserByEmail("unknown@example.com");
  }
}
