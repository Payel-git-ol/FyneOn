package com.example.postmainfyneon.service;

import com.example.postmainfyneon.model.hash.PostHash;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Service
public class PostRedisService {

    private final RedisTemplate<String, Object> postRedisTemplate;

    public PostRedisService(@Qualifier("postRedisRawTemplate") RedisTemplate<String, Object> postRedisTemplate) {
        this.postRedisTemplate = postRedisTemplate;
    }

    public void save(PostHash event) {
        PostHash post = new PostHash();
        post.setSender(event.getSender());
        post.setContent(event.getContent());
        post.setName(event.getName());
        post.setReceivedAt(LocalDateTime.now());

        String key = "post:" + event.getSender() + ":" + post.getReceivedAt();
        postRedisTemplate.opsForValue().set(key, post);
    }

    public List<PostHash> getAllPost() {
        Set<String> keys = postRedisTemplate.keys("post*");
        List<PostHash> posts = new ArrayList<>();

        if (keys != null) {
            for (String key : keys) {
                Object value = postRedisTemplate.opsForValue().get(key);
                if (value instanceof LinkedHashMap map) {
                    PostHash post = new PostHash();
                    post.setSender((String) map.get("sender"));
                    post.setContent((String) map.get("content"));
                    post.setName((String) map.get("name"));

                    Object receivedAtRaw = map.get("receivedAt");
                    if (receivedAtRaw instanceof String receivedAtStr) {
                        post.setReceivedAt(LocalDateTime.parse(receivedAtStr));
                    }

                    posts.add(post);
                }
            }
        }

        return posts;
    }

}