package com.alok.repository;

import com.alok.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<StockData, Long> {
}
