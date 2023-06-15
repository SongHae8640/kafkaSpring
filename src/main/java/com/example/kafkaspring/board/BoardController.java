package com.example.kafkaspring.board;

import com.example.kafkaspring.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;


    @PostMapping
    public Board createBoard(@RequestBody BoardDto boardDto) {
        return boardService.saveBoard(boardDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }
}