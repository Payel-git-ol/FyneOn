package com.example.postmainfyneon.dto;

import com.example.postmainfyneon.model.event.UserEvent;
import com.example.postmainfyneon.model.hash.UsersHash;
import com.example.postmainfyneon.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerUsers {
    private final UserService userService;

    public KafkaConsumerUsers(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user_created", groupId = "post-service-group", containerFactory = "userEventKafkaListenerContainerFactory")
    public void consume(UserEvent event) {
        try {
            System.out.println("📥 ПОЛУЧЕНО СОБЫТИЕ: user_created");
            System.out.println("   ID: " + event.getId());
            System.out.println("   Email: " + event.getEmail());
            System.out.println("   Имя: " + event.getUsername());

            UsersHash user = new UsersHash();
            user.setId(event.getId().intValue());
            user.setEmail(event.getEmail());
            user.setName(event.getUsername());

            userService.save(user);
            System.out.println("✅ Пользователь сохранён в Redis");
        } catch (Exception e) {
            System.err.println("❌ ОШИБКА В КОНСЮМЕРЕ");
            e.printStackTrace();
        }
    }

}
