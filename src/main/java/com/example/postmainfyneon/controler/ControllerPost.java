package com.example.postmainfyneon.controler;

import com.example.postmainfyneon.model.hash.PostHash;
import com.example.postmainfyneon.service.PostRedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerPost {


    private final PostRedisService postRedisService;

    public ControllerPost(PostRedisService postRedisService) {
        this.postRedisService = postRedisService;
    }


    @GetMapping("/posts")
    public List<PostHash> getPosts() {
        return postRedisService.getAllPost();
    }
}
