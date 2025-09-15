package com.example.postmainfyneon.config.redisconfig;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "custom.redis-usercache")
public class RedisPostCacheProperties {
    private String host;
    private int port;

    @PostConstruct
    public void debag() {
        System.out.println("🔧 RedisPostCacheProperties: host=" + host + ", port=" + port);
    }
}
