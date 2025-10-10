package com.example.kafkasample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    //bu anotasiya ile "ilk-movzu" topic-ine mesaj gelende avtomatik ise dusur
    @KafkaListener(topics = "ilk-movzu", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Kafka'dan yeni mesaj qəbul edildi: " + message);
    }
}
