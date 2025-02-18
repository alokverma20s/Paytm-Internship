package com.icwd.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icwd.hotel.entities.Hotel;
import com.icwd.hotel.services.HotelServices;

@RestController
@RequestMapping("/hotels")
public class HotelController {
  @Autowired
  private HotelServices hotelServices;

  @PostMapping
  public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
    Hotel response = hotelServices.create(hotel);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Hotel>> getAllHotels() {
    List<Hotel> hotels = hotelServices.getAll();
    return new ResponseEntity<>(hotels, HttpStatus.OK);
  }

  @GetMapping("/{hotelId}")
  public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
    Hotel hotel = hotelServices.getById(hotelId);
    return new ResponseEntity<>(hotel, HttpStatus.OK);
  }

  @PutMapping("/{hotelId}")
  public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel) {
    Hotel response = hotelServices.update(hotelId, hotel);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{hotelId}")
  public ResponseEntity<String> deleteHotel(@PathVariable String hotelId) {
    hotelServices.delete(hotelId);
    return ResponseEntity.ok("Hotel is deleted");
  }
}
