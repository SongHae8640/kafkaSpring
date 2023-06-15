package com.example.kafkaspring.board;

import com.example.kafkaspring.board.event.BoardCreatedEvent;
import com.example.kafkaspring.board.event.BoardDeletedEvent;
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
    public void sendBoardCreatedEvent(BoardCreatedEvent boardCreatedEvent) {
        kafkaProducer.send(Topic.BOARD_CREATED, boardCreatedEvent);
    }

    @Async
    @EventListener
    public void sendBoardDeletedEvent(BoardDeletedEvent boardDeletedEvent) {
        kafkaProducer.send(Topic.BOARD_DELETED, boardDeletedEvent);
    }

    @Async
    @EventListener
    public void deleteCommentByBoard(BoardDeletedEvent boardDeletedEvent) {
        System.out.println("Board에 속한 Comment 삭제");
    }

}