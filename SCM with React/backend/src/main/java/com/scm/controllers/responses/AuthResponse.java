package com.scm.controllers.responses;

import com.scm.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
  private String token;
  private User user;
  private String error;
  public AuthResponse(String token, User user) {
    this.token = token;
    this.user = user;
  }
}
