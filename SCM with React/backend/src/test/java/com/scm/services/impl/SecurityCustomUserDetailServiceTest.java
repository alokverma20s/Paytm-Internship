package com.scm.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.scm.entities.User;
import com.scm.repsitories.UserRepo;

@ExtendWith(MockitoExtension.class)
class SecurityCustomUserDetailServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(123L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
    }

    @Test
    void testLoadUserByUsername_Success() {
        // Mocking the user repository behavior
        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Calling the method
        UserDetails userDetails = securityCustomUserDetailService.loadUserByUsername("test@example.com");

        // Assertions
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        verify(userRepo, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mocking an empty response from the repository
        when(userRepo.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        // Expecting an exception
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            securityCustomUserDetailService.loadUserByUsername("unknown@example.com");
        });

        // Assertions
        assertTrue(exception.getMessage().contains("User not found with email"));
        verify(userRepo, times(1)).findByEmail("unknown@example.com");
    }
}
