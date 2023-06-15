package com.example.kafkaspring.board;

import com.example.kafkaspring.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board saveBoard(BoardDto boardDto) {
        Board board = boardRepository.save(boardDto.createBoard());
        System.out.println("saveBoard :: board = " + board);
        return board;
    }

    public Long deleteBoard(Long id) {
        boardRepository.deleteById(id);
        System.out.println("deleteBoard :: id = " + id);
        return id;
    }
}