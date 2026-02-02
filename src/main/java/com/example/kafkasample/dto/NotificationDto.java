package com.example.kafkasample.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NotificationDto implements Serializable {
    private String title;
    private String message;
    private String recipient;
    private LocalDateTime timestamp;

    public NotificationDto() {
    }

    public NotificationDto(String title, String message, String recipient) {
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", recipient='" + recipient + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
