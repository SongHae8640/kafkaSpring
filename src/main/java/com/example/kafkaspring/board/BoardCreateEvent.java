package com.example.kafkaspring.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardCreateEvent {
    private final BoardCategory category;
    private final String title;
    private final String content;
}