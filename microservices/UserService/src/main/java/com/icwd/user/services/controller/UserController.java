package com.icwd.user.services.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icwd.user.services.entities.User;
import com.icwd.user.services.services.UserServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserServices userServices;

  private final static Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    String userId = UUID.randomUUID().toString();
    user.setUserId(userId);
    User savedUser = userServices.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }
  int retryCount=0;

  @GetMapping("/{userId}")
  // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
  // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
  @RateLimiter(name = "ratingHotelLimiter", fallbackMethod = "ratingHotelFallback")
  public ResponseEntity<User> getUser(@PathVariable String userId) {
    logger.info("Retry Count: {}", retryCount++);
    User user = userServices.getUserById(userId);
    return ResponseEntity.ok(user);
  }

  // creating fall back method for circuit breaker
  public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
    logger.info("Fallback is executed because service is down.", ex.getMessage());
    User user = new User();
    user.setUserId(userId);
    user.setName("Dummy User");
    user.setEmail("dummy@gmail.com");
    user.setAbout("This user is created dummy because some services are down");
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userServices.getAllUsers();
    return ResponseEntity.ok(users);
  }
}
