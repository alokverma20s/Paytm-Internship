package com.icwd.user.services.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.icwd.user.services.entities.Rating;
@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingServices {

  @PostMapping("/rating")
  Rating createRating(Rating rating);

  @GetMapping("/rating/user/{userId}")
  List<Rating> getRating(@PathVariable String userId);
}
