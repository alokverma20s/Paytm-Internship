package com.icwd.rating.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icwd.rating.entities.Rating;
import com.icwd.rating.repositories.RatingRepository;
import com.icwd.rating.services.RatingServices;

@Service
public class RatingServicesImpl implements RatingServices{

  @Autowired
  private RatingRepository ratingRepository;
  @Override
  public Rating createRating(Rating rating) {
    return ratingRepository.save(rating);
  }

  @Override
  public List<Rating> getAll() {
    return ratingRepository.findAll();
  }

  @Override
  public List<Rating> getRatingByUserId(String userId) {
    return ratingRepository.findByUserId(userId);
  }

  @Override
  public List<Rating> getRatingByHotelId(String hotelId) {
    return ratingRepository.findByHotelId(hotelId);
  }

  @Override
  public Rating updateRating(String ratingId, Rating rating) {
    rating.setRatingId(ratingId);
    return ratingRepository.save(rating);
  }

  @Override
  public void deleteRating(String ratingId) {
    Rating byId = ratingRepository.findById(ratingId).orElse(null);
    ratingRepository.delete(byId);
  }
}
