package com.icwd.user.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.icwd.user.services.entities.Hotel;
import com.icwd.user.services.entities.Rating;
import com.icwd.user.services.entities.User;
import com.icwd.user.services.external.services.HotelServices;
import com.icwd.user.services.external.services.RatingServices;
import com.icwd.user.services.repositories.UserRepository;
import com.icwd.user.services.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

  @Autowired
  private UserRepository userRepository;

  /**
   * Autowired instance of HotelServices to handle hotel-related operations.
   */
  @Autowired
  private HotelServices hotelServices;

  @Autowired
  private RatingServices ratingServices;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceAccessException("User not found with id " + userId));
    // fetch the rating given by the user by fetching the following url
    List<Rating> ratings = ratingServices.getRating(userId);

    if (ratings == null) {
      throw new ResourceAccessException("Ratings not found for user with id " + userId);
    }
    List<Rating> ratingList = ratings.stream().map(rating -> {
      // api call to hotel service to get the hotel
      Hotel hotel = hotelServices.getHotelById(rating.getHotelId());
      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());

    user.setRatings(ratingList);
    return user;
  }

  @Override
  public void deleteUser(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceAccessException("User not found with id " + userId));
    userRepository.delete(user);
  }

  @Override
  public User updateUser(String userId, User updatedUser) {
    userRepository.findById(userId)
        .orElseThrow(() -> new ResourceAccessException("User not found with id " + userId));
    updatedUser.setUserId(userId);
    return userRepository.save(updatedUser);
  }

}
