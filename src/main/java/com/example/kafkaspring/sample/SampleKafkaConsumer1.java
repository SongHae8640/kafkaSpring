package com.example.kafkaspring.sample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SampleKafkaConsumer1 {

    @KafkaListener(topics = "sample", groupId = "consumerGroup1")
    public void consume(String message) {
        System.out.println("KafkaConsumer1 :: Consumed message: " + message);
    }
}