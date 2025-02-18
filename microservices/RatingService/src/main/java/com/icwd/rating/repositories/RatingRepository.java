package com.icwd.rating.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.icwd.rating.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating , String> {
  // custom finder implementation
  List<Rating> findByUserId(String userId);
  List<Rating> findByHotelId(String hotelId);
}
