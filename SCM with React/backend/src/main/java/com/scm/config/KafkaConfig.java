package com.scm.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.scm.helpers.AppConstants;

@Configuration
public class KafkaConfig {
    
    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstants.KAFKA_TOPIC)
                // .partitions(1)
                // .replicas(1)
                .build();
    }
}
