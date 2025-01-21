package com.alok;

import com.alok.entity.StockData;
import com.alok.model.Stock;
import com.alok.repository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDatabaseConsumer {
    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }


    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(Stock message){
        StockData stockData = new StockData();
        stockData.setSymbol(message.getSymbol());
        stockData.setVolume(message.getVolume());
        stockData.setPrice(message.getPrice());

        StockData save = wikimediaDataRepository.save(stockData);
    }
}
