package com.icwd.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icwd.rating.entities.Rating;
import com.icwd.rating.services.RatingServices;

@RestController
@RequestMapping("/rating")
public class RatingController {
  @Autowired
  private RatingServices ratingServices;

  @PostMapping
  public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
    System.out.println(rating);
    Rating savedRating = ratingServices.createRating(rating);
    return ResponseEntity.status(201).body(savedRating);
  }

  @GetMapping
  public ResponseEntity<List<Rating>> getAllRatings() {
    List<Rating> ratings = ratingServices.getAll();
    return ResponseEntity.ok(ratings);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
    List<Rating> ratings = ratingServices.getRatingByUserId(userId);
    return ResponseEntity.ok(ratings);
  }

  @GetMapping("/hotel/{hotelId}")
  public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
    List<Rating> ratings = ratingServices.getRatingByHotelId(hotelId);
    return ResponseEntity.ok(ratings);
  }

  @PutMapping("/{ratingId}")
  public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating) {
    Rating updatedRating = ratingServices.updateRating(ratingId, rating);
    return ResponseEntity.ok(updatedRating);
  }

  @DeleteMapping("/{ratingId}")
  public ResponseEntity<String> deleteRating(@PathVariable String ratingId) {
    ratingServices.deleteRating(ratingId);
    return ResponseEntity.ok("Rating is deleted");
  }
}
