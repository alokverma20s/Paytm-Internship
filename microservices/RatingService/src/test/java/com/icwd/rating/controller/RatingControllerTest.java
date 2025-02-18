package com.icwd.rating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icwd.rating.entities.Rating;
import com.icwd.rating.services.RatingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RatingControllerTest {

  private static final Logger logger = LoggerFactory.getLogger(RatingControllerTest.class);

  private MockMvc mockMvc;

  @Mock
  private RatingServices ratingServices;

  @InjectMocks
  private RatingController ratingController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    logger.info("Setting up MockMvc and initializing mocks...");
  }

  @Test
  void testCreateRating() throws Exception {
    Rating rating = new Rating("1", "user1", "hotel1", 5, "Excellent stay!");
    when(ratingServices.createRating(any(Rating.class))).thenReturn(rating);

    logger.info("Running test: testCreateRating");

    mockMvc.perform(post("/rating")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(rating)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.ratingId").value("1"))
        .andExpect(jsonPath("$.userId").value("user1"))
        .andExpect(jsonPath("$.hotelId").value("hotel1"))
        .andExpect(jsonPath("$.rating").value(5))
        .andExpect(jsonPath("$.feedback").value("Excellent stay!"));

    logger.info("testCreateRating completed successfully.");
  }

  @Test
  void testGetAllRatings() throws Exception {
    List<Rating> ratings = Arrays.asList(
        new Rating("1", "user1", "hotel1", 5, "Great!"),
        new Rating("2", "user2", "hotel2", 4, "Good service"));
    when(ratingServices.getAll()).thenReturn(ratings);

    logger.info("Running test: testGetAllRatings");

    mockMvc.perform(get("/rating"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2));

    logger.info("testGetAllRatings completed successfully.");
  }

  @Test
  void testGetRatingsByUserId() throws Exception {
    List<Rating> ratings = List.of(new Rating("1", "user1", "hotel1", 5, "Great!"));
    when(ratingServices.getRatingByUserId("user1")).thenReturn(ratings);

    logger.info("Running test: testGetRatingsByUserId");

    mockMvc.perform(get("/rating/user/user1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1))
        .andExpect(jsonPath("$[0].userId").value("user1"));

    logger.info("testGetRatingsByUserId completed successfully.");
  }

  @Test
  void testGetRatingsByHotelId() throws Exception {
    List<Rating> ratings = List.of(new Rating("1", "user1", "hotel1", 5, "Great!"));
    when(ratingServices.getRatingByHotelId("hotel1")).thenReturn(ratings);

    logger.info("Running test: testGetRatingsByHotelId");

    mockMvc.perform(get("/rating/hotel/hotel1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1))
        .andExpect(jsonPath("$[0].hotelId").value("hotel1"));

    logger.info("testGetRatingsByHotelId completed successfully.");
  }

  @Test
  void testUpdateRating() throws Exception {
    Rating updatedRating = new Rating("1", "user1", "hotel1", 4, "Updated feedback");
    when(ratingServices.updateRating(eq("1"), any(Rating.class))).thenReturn(updatedRating);

    logger.info("Running test: testUpdateRating");

    mockMvc.perform(put("/rating/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedRating)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.rating").value(4))
        .andExpect(jsonPath("$.feedback").value("Updated feedback"));

    logger.info("testUpdateRating completed successfully.");
  }

  @Test
  void testDeleteRating() throws Exception {
    doNothing().when(ratingServices).deleteRating("1");

    logger.info("Running test: testDeleteRating");

    mockMvc.perform(delete("/rating/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Rating is deleted"));

    logger.info("testDeleteRating completed successfully.");
  }
}
