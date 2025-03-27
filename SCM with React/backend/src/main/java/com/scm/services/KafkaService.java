package com.scm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.scm.DTO.ContactDTO;
import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repsitories.ContactRepo;

@Service
public class KafkaService {

    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private UserService userService;
    private final KafkaTemplate<String, ContactDTO> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    // ✅ Constructor Injection
    public KafkaService(KafkaTemplate<String, ContactDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean saveContact(ContactDTO contact) {
        try {
            kafkaTemplate.send(AppConstants.KAFKA_TOPIC, contact);
            logger.info("Contact saved to Kafka topic: " + AppConstants.KAFKA_TOPIC);
            return true;
        } catch (Exception e) {
            logger.error("Failed to send contact to Kafka", e);
            return false;
        }
    }

    @KafkaListener(topics = AppConstants.KAFKA_TOPIC, groupId = AppConstants.KAFKA_GROUP_ID)
    public void listen(ContactDTO contactDTO) {
        logger.info("Received contact from Kafka: " + contactDTO);
        Contact contact = ContactDTO.toEntity(contactDTO);
        User user = userService.getUserByEmail(contactDTO.getUserId());
        contact.setUser(user);
        contactRepo.save(contact);
    }

}
