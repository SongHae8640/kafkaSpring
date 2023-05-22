package com.example.kafkaspring.sample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "sample", groupId = "consumerGroup")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
