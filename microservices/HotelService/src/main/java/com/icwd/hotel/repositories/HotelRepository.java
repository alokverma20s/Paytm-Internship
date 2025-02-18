package com.icwd.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icwd.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
