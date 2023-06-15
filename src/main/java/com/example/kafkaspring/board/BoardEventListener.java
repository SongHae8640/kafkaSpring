package com.example.kafkaspring.board;

import com.example.kafkaspring.kafka.KafkaProducer;
import com.example.kafkaspring.kafka.Topic;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BoardEventListener {

    private final KafkaProducer kafkaProducer;

    @Async
    @EventListener
    public void handleBoardCreatedEvent(BoardCreatedEvent boardCreatedEvent) {
        kafkaProducer.send(Topic.BOARD_CREATED, boardCreatedEvent);
    }
}