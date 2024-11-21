package com.noticeboard.Board.service;

import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void newBoard(BoardDTO boardDTO) {
        boardRepository.newBoard(boardDTO);
    }
}
