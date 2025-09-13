package com.example.fyneonauth.dto;

import com.example.fyneonauth.model.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String USER_CREATED_TOPIC = "user_created";
    private static final String USER_UPDATED_TOPIC = "user_updated";
    private static final String USER_DELETED_TOPIC = "user_deleted";

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserEvent event) {
        try {
            kafkaTemplate.send(USER_CREATED_TOPIC, event);
            System.out.println("✅ СОБЫТИЕ ОТПРАВЛЕНО В KAFKA");
            System.out.println("   Топик: " + USER_CREATED_TOPIC);
            System.out.println("   Тип: Создание пользователя");
            System.out.println("   ID пользователя: " + event.getId());
            System.out.println("   Email: " + event.getEmail());
            System.out.println("   Имя: " + event.getUsername());
            System.out.println("   Время: " + java.time.LocalDateTime.now());
            System.out.println("─────────────────────────────────────────");
        } catch (Exception e) {
            System.err.println("❌ ОШИБКА ОТПРАВКИ В KAFKA");
            System.err.println("   Топик: " + USER_CREATED_TOPIC);
            System.err.println("   Причина: " + e.getMessage());
            System.err.println("─────────────────────────────────────────");
        }
    }

    public void sendUserUpdatedEvent(UserEvent event) {
        try {
            kafkaTemplate.send(USER_UPDATED_TOPIC, event);
            System.out.println("✅ СОБЫТИЕ ОТПРАВЛЕНО В KAFKA");
            System.out.println("   Топик: " + USER_UPDATED_TOPIC);
            System.out.println("   Тип: Обновление пользователя");
            System.out.println("   ID пользователя: " + event.getId());
            System.out.println("   Email: " + event.getEmail());
            System.out.println("   Новое имя: " + event.getUsername());
            System.out.println("   Время: " + java.time.LocalDateTime.now());
            System.out.println("─────────────────────────────────────────");
        } catch (Exception e) {
            System.err.println("❌ ОШИБКА ОТПРАВКИ В KAFKA");
            System.err.println("   Топик: " + USER_UPDATED_TOPIC);
            System.err.println("   Причина: " + e.getMessage());
            System.err.println("─────────────────────────────────────────");
        }
    }

    public void sendUserDeletedEvent(UserEvent event) {
        try {
            kafkaTemplate.send(USER_DELETED_TOPIC, event);
            System.out.println("✅ СОБЫТИЕ ОТПРАВЛЕНО В KAFKA");
            System.out.println("   Топик: " + USER_DELETED_TOPIC);
            System.out.println("   Тип: Удаление пользователя");
            System.out.println("   ID пользователя: " + event.getId());
            System.out.println("   Email: " + event.getEmail());
            System.out.println("   Имя: " + event.getUsername());
            System.out.println("   Время: " + java.time.LocalDateTime.now());
            System.out.println("─────────────────────────────────────────");
        } catch (Exception e) {
            System.err.println("❌ ОШИБКА ОТПРАВКИ В KAFKA");
            System.err.println("   Топик: " + USER_DELETED_TOPIC);
            System.err.println("   Причина: " + e.getMessage());
            System.err.println("─────────────────────────────────────────");
        }
    }

    // Дополнительный метод для общей информации
    public void printKafkaStatus() {
        System.out.println("📊 СТАТУС KAFKA PRODUCER");
        System.out.println("   Активные топики:");
        System.out.println("   - " + USER_CREATED_TOPIC + " (создание пользователей)");
        System.out.println("   - " + USER_UPDATED_TOPIC + " (обновление пользователей)");
        System.out.println("   - " + USER_DELETED_TOPIC + " (удаление пользователей)");
        System.out.println("   Bootstrap servers: localhost:9092");
        System.out.println("─────────────────────────────────────────");
    }
}