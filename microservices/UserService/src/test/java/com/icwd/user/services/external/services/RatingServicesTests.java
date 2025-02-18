package com.icwd.user.services.external.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.icwd.user.services.entities.Rating;

@SpringBootTest
class RatingServicesTests {

  @Autowired
  private RatingServices ratingServices;

  // Test cases for rating service here
  @Test
  void createRating() {
    Rating rating = Rating.builder()
        .userId("testUser")
        .hotelId("testHotel")
        .rating(5)
        .feedback("Great hotel!")
        .build();
    Rating savedRating = ratingServices.createRating(rating);
    assertNotNull(savedRating, "The saved rating should not be null");
    assertEquals("testUser", savedRating.getUserId(), "The userId should be 'testUser'");
    assertEquals("testHotel", savedRating.getHotelId(), "The hotelId should be 'testHotel'");
    assertEquals(5, savedRating.getRating(), "The rating should be 5");
    assertEquals("Great hotel!", savedRating.getFeedback(), "The feedback should be 'Great hotel!'");
  }
}
