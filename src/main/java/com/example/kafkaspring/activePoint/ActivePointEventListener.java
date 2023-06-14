package com.example.kafkaspring.activePoint;

import com.example.kafkaspring.board.BoardCreateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class ActivePointEventListener {

    @Async
    @EventListener
    public void giveActivePoint(BoardCreateEvent boardCreateEvent) {
        System.out.println("글 작성자에게 포인트 지급");

    }

}