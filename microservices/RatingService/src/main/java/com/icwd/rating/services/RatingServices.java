package com.icwd.rating.services;

import java.util.List;

import com.icwd.rating.entities.Rating;

public interface RatingServices {

  Rating createRating(Rating rating);

  List<Rating> getAll();

  List<Rating> getRatingByUserId(String userId);

  List<Rating> getRatingByHotelId(String hotelId);

  Rating updateRating(String ratingId,Rating rating);

  void deleteRating(String ratingId);
  
}
