package com.example.kafkaspring.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum BoardCategory {
    JOIN("JOIN", "같이가요"),
    INFO("INFO", "정보&소식"),
    ;

    private final String category;
    private final String description;

    public static BoardCategory of(String category) {
        for (BoardCategory boardCategory : values()) {
            if (boardCategory.category.equals(category)) {
                return boardCategory;
            }
        }
        throw new IllegalArgumentException("Invalid BoardCategory: " + category);
    }

}