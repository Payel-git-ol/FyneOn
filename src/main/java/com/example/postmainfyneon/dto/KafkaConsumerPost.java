package com.example.postmainfyneon.dto;

import com.example.postmainfyneon.model.event.PostEvent;
import com.example.postmainfyneon.model.hash.PostHash;
import com.example.postmainfyneon.service.PostRedisService;
import com.example.postmainfyneon.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerPost {
    private final PostRedisService postRedisService;

    public KafkaConsumerPost(PostRedisService postRedisService) {
        this.postRedisService = postRedisService;
    }

    @KafkaListener(topics = "posts-topic", groupId = "post-service-group", containerFactory = "postEventKafkaListenerContainerFactory")
    public void consumePosts(PostEvent event) {
        try {
            System.out.println("📥 ПОЛУЧЕНО СООБЩЕНИЕ ИЗ KAFKA");
            System.out.println("   Отправитель: " + event.getSender());
            System.out.println("   Контент: " + event.getContent());
            System.out.println("   Время: " + java.time.LocalDateTime.now());
            System.out.println("─────────────────────────────────────────");

            PostHash posts = new PostHash();
            posts.setSender(event.getSender());
            posts.setContent(event.getContent());
            posts.setName(event.getName());

            postRedisService.save(posts);

            System.out.println("✅ Пост сохранён в Redis");
            System.out.println("   Контент: " + event.getContent());
            System.out.println("   Время: " + java.time.LocalDateTime.now());
            System.out.println("─────────────────────────────────────────");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
