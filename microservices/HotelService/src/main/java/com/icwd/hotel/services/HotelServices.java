package com.icwd.hotel.services;

import java.util.List;

import com.icwd.hotel.entities.Hotel;

public interface HotelServices {

  Hotel create(Hotel hotel);


  Hotel getById(String hotelId);

  Hotel update(String hotelId,Hotel hotel);

  void delete(String hotelId);

  List<Hotel> getAll();
  
}
