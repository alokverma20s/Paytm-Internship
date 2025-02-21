package com.scm.controllers;

import com.scm.entities.User;
import com.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Get details of the currently logged-in user
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        // Fetch user from database using email
        User user = userService.getUserByEmail(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.notFound().build(); // Return 404 if user is not found
        }
        user.setContacts(null);
        return ResponseEntity.ok(user);
    }
}