package com.example.kafkaspring.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {
    BOARD_CREATED("community.boardCreated", "게시글 생성"),
    ;

    private final String topic;
    private final String description;
}
