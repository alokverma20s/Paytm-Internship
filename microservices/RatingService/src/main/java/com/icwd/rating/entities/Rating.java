package com.icwd.rating.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user_ratings")
public class Rating {
  @Id
  private String ratingId;
  private String userId;
  private String hotelId;
  private int rating;
  private String feedback;
}
