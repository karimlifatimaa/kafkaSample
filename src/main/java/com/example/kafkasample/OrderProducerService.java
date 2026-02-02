package com.example.kafkasample;

import com.example.kafkasample.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Value("${app.kafka.topic}")
    private String topicName;

    private final Random random = new Random();
    private final AtomicLong orderIdGenerator = new AtomicLong(100);

    @Autowired
    public OrderProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Her 2 saniyeden bir ise dusur
    @Scheduled(fixedRate = 2000)
    public void generateOrder() {
        // Random user id (user_1 ... user_5)
        String userId = "user_" + (random.nextInt(5) + 1);
        
        // Random mebleg (10 - 2000 arasi)
        double amount = 10 + (2000 - 10) * random.nextDouble();
        
        OrderDto order = new OrderDto(
                orderIdGenerator.getAndIncrement(), 
                Math.round(amount * 100.0) / 100.0, // 2 reqem yuvarlaq
                "CREATED"
        );

        System.out.println("------------------------------------------------");
        System.out.println("PRODUCER: Yeni sifaris yaradildi: " + order + " | User: " + userId);
        
        // Key (userId) ile birlikde gonderirik
        kafkaTemplate.send(topicName, userId, order);
    }
}
