package com.example.kafkaspring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(Topic topic, Object object) {
        String message;
        try {
            message = objectMapper.writeValueAsString(object);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        kafkaTemplate.send(topic.getTopic(), message);
        System.out.println(topic + ", message: " + message);
    }
}
