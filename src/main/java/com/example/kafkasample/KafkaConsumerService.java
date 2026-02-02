package com.example.kafkasample;

import com.example.kafkasample.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "my-group")
    public void listen(NotificationDto notification) {
        logger.info("Kafka-dan yeni notification qebul edildi: {}", notification);
        logger.info("Basliq: {}", notification.getTitle());
        logger.info("Mesaj: {}", notification.getMessage());
        logger.info("Alan: {}", notification.getRecipient());
    }
}
