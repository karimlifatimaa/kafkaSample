package com.example.kafkasample;

import com.example.kafkasample.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FraudDetectionConsumer.class);

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "fraud-detection-group")
    public void analyzeOrder(@Payload OrderDto order, 
                             @Header(KafkaHeaders.RECEIVED_KEY) String userId) {
        
        logger.info("CONSUMER: Sifaris qebul edildi. User: {}, Amount: {}", userId, order.getAmount());

        if (order.getAmount() > 1000) {
            logger.warn(">>> DIQQET: Subheli sifaris! User: {} | Amount: {} - ANALIZ TELEB OLUNUR", userId, order.getAmount());
        } else {
            logger.info(">>> Sifaris tesdiqlendi. User: {}", userId);
        }
    }
}
