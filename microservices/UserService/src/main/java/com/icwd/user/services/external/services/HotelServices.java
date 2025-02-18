package com.icwd.user.services.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.icwd.user.services.entities.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelServices {

  @PostMapping("/hotels")
  Hotel createHotel(Hotel hotel);

  @GetMapping("/hotels")
  List<Hotel> getHotel();

  @GetMapping("/hotels/{hotelId}")
  Hotel getHotelById(@PathVariable String hotelId);

  @PutMapping("/hotels/{hotelId}")
  Hotel updateHotel(@PathVariable String hotelId, Hotel updatedHotel);


  @DeleteMapping("/hotels/{hotelId}")
  void deleteHotel(@PathVariable String hotelId);
}
