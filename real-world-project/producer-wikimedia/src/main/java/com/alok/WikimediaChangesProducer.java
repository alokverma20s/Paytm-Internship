package com.alok;

import com.alok.model.Stock;
import com.alok.service.StockService;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private StockService stockService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() {
        // to read real time stream data from wikimedia, we use event source
        scheduler.scheduleAtFixedRate(() -> {
            Stock stock = stockService.generateRandomStock();
            Message<Stock> message = MessageBuilder
                    .withPayload(stock)
                    .setHeader(KafkaHeaders.TOPIC, Constants.topic)
                    .build();
            kafkaTemplate.send(message);
        }, 0, 1, TimeUnit.SECONDS);
    }
}
