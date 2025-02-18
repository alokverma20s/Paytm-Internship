package com.scm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.entities.User;
import com.scm.repsitories.UserRepo;
import com.scm.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

  private static final Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);

  private MockMvc mockMvc;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtUtil jwtUtil;

  @Mock
  private UserRepo userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private AuthController authController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    logger.info("MockMvc setup completed.");
  }

  @Test
  void testLogin_Success() throws Exception {
    // Arrange
    String email = "test@example.com";
    String password = "password";
    String token = "fake-jwt-token";
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
    when(jwtUtil.generateToken(email)).thenReturn(token);
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

    logger.info("Running test: testLogin_Success");

    // Act & Assert
    mockMvc.perform(post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(
            Map.of("email", email, "password", password))))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").value(token))
        .andExpect(jsonPath("$.user.email").value(email));

    logger.info("testLogin_Success completed successfully.");
  }

  @Test
  void testLogin_Failure() throws Exception {
    // Arrange
    String email = "test@example.com";
    String password = "wrong-password";

    doThrow(new RuntimeException("Bad credentials"))
        .when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

    when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    logger.info("Running test: testLogin_Failure");

    // Act & Assert
    mockMvc.perform(post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(
            Map.of("email", email, "password", password))))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Invalid credentials"));

    logger.info("testLogin_Failure completed successfully.");
  }

  @Test
  void testRegister_Success() throws Exception {
    // Arrange
    User user = new User();
    user.setEmail("newuser@example.com");
    user.setPassword("password");

    when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded-password");
    when(userRepository.save(any(User.class))).thenReturn(user);

    logger.info("Running test: testRegister_Success");

    // Act & Assert
    mockMvc.perform(post("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isCreated())
        .andExpect(content().string("User created successfully"));

    logger.info("testRegister_Success completed successfully.");
  }
}
