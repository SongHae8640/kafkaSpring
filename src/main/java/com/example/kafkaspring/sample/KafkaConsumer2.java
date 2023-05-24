package com.example.kafkaspring.sample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer2 {

    @KafkaListener(topics = "sample", groupId = "consumerGroup2")
    public void consume(String message) {
        System.out.println("KafkaConsumer2 :: Consumed message: " + message);
    }
}
