package com.icwd.user.services.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "micro_users")
@Data
public class User {
  @Id
  private String userId;
  private String name;
  private String email;
  private String about;
  @Transient
  private List<Rating> ratings = new ArrayList<>();
}
