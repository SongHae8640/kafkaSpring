package com.example.kafkaspring.board;

import com.example.kafkaspring.board.event.BoardCreatedEvent;
import com.example.kafkaspring.board.event.BoardDeletedEvent;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class BoardAspect {

    ApplicationEventPublisher publisher;

    @AfterReturning(value = "execution(* com.example.kafkaspring.board.BoardController.createBoard(..))", returning = "board")
    public void afterSaveBoard(Board board) {
        BoardCreatedEvent boardCreatedEvent = BoardCreatedEvent.builder()
                .id(board.getId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .build();

        System.out.println("publishEvent :: boardCreatedEvent = " + boardCreatedEvent);
        publisher.publishEvent(boardCreatedEvent);
    }

    @AfterReturning(value = "execution(* com.example.kafkaspring.board.BoardController.deleteBoard(..))", returning = "id")
    public void afterDeleteBoard(Long id) {
        BoardDeletedEvent boardDeletedEvent = BoardDeletedEvent.builder()
                .id(id)
                .build();

        System.out.println("publishEvent :: boardDeletedEvent = " + boardDeletedEvent);
        publisher.publishEvent(boardDeletedEvent);
    }
}