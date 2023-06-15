package com.example.kafkaspring.board.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardDeletedEvent {
    private final Long id;
}