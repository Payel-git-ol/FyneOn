package com.example.fyneonauth.configuration;

import com.example.fyneonauth.dto.KafkaProducer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaStartupNotifier {
    private final KafkaProducer kafkaProducer;

    public KafkaStartupNotifier(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        kafkaProducer.printKafkaStatus();
    }
}
