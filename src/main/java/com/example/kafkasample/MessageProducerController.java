package com.example.kafkasample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class MessageProducerController {
    // Kafka ilə əməliyyatlar üçün Spring-in təqdim etdiyi hazır şablon (template).
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Mesajların göndəriləcəyi topic-in adı.
    private static final String TOPIC = "ilk-movzu";

    @Autowired
    public MessageProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // HTTP POST sorğusu ilə mesaj göndərmək üçün endpoint.
    // Məsələn: POST http://localhost:8080/api/messages/send?message=salam
    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        try {
            // kafkaTemplate.send() metodu ilə mesajı müvafiq topic-ə göndəririk.
            kafkaTemplate.send(TOPIC, message);
            return "Mesaj uğurla göndərildi: '" + message + "'";
        } catch (Exception e) {
            return "Mesaj göndərilərkən xəta baş verdi: " + e.getMessage();
        }
    }
}
