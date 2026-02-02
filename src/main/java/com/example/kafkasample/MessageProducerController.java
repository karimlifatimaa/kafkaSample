package com.example.kafkasample;

import com.example.kafkasample.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class MessageProducerController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topicName;

    @Autowired
    public MessageProducerController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // http://localhost:8080/api/kafka/send?title=Salam&message=Test&recipient=User1
    @PostMapping("/send")
    public String sendMessage(@RequestParam("title") String title,
            @RequestParam("message") String message,
            @RequestParam("recipient") String recipient) {
        try {
            // DTO yaradiriq
            NotificationDto notification = new NotificationDto(title, message, recipient);

            // Obyekti gonderirik
            kafkaTemplate.send(topicName, notification);

            return "Mesaj ugurla gonderildi: " + notification.toString();
        } catch (Exception e) {
            return "Mesaj gonderilerken xeta bas verdi: " + e.getMessage();
        }
    }
}
