package com.example.kafkaspring.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardCreatedEvent {
    private final Long id;
    private final BoardCategory category;
    private final String title;
    private final String content;
}