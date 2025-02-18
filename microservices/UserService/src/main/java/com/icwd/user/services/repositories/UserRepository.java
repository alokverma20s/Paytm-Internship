package com.icwd.user.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icwd.user.services.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
  
}
