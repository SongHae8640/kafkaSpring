package com.example.kafkaspring.pushAlarm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushAlarmService {

    private static final String TOPIC = "pushAlarm";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void saveList(List<PushAlarm> pushAlarmList) {
        pushAlarmList.forEach(pushAlarm -> {
            try {
                String message = objectMapper.writeValueAsString(pushAlarm);
                System.out.printf("sendMessage :: topic = %s ,message = %s\n" ,TOPIC, message);
                kafkaTemplate.send(TOPIC, message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}