package com.example.kafkaspring.board.dto;

import com.example.kafkaspring.board.Board;
import com.example.kafkaspring.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private String title;
    private String content;
    private String category;

    public Board createBoard(){
        return Board.builder()
                .category(BoardCategory.of(category))
                .title(title)
                .content(content)
                .build();
    }
}