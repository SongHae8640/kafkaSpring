package com.example.kafkaspring.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleKafkaProducer {
    private static final String TOPIC = "sample";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println("sendMessage message(start): " + message);
        this.kafkaTemplate.send(TOPIC, message);
        System.out.println("sendMessage message(end): " + message);
    }
}
