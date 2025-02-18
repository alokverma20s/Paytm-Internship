package com.icwd.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icwd.hotel.entities.Hotel;
import com.icwd.hotel.exception.ResourceNotFoundException;
import com.icwd.hotel.repositories.HotelRepository;
import com.icwd.hotel.services.HotelServices;

@Service
public class HotelServiceImpl implements HotelServices {

  @Autowired
  private HotelRepository hotelRepository;

  @Override
  public Hotel create(Hotel hotel) {
    String id = UUID.randomUUID().toString();
    hotel.setHotelId(id);
    return hotelRepository.save(hotel);
  }

  @Override
  public Hotel getById(String hotelId) {
    Hotel hotel = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id " + hotelId));
    return hotel;
  }

  @Override
  public Hotel update(String hotelId, Hotel hotel) {
    hotel.setHotelId(hotelId);
    return hotelRepository.save(hotel);
  }

  @Override
  public void delete(String hotelId) {
    hotelRepository.findById(hotelId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id " + hotelId));

    hotelRepository.deleteById(hotelId);
  }

  @Override
  public List<Hotel> getAll() {
    return hotelRepository.findAll();
  }

}
