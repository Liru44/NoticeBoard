package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/newBoard")
    public String newBoard() {
        return "newBoard";
    }

    @PostMapping("/newBoard")
    public void newBoard(BoardDTO boardDTO) {
        System.out.println(boardDTO);
        boardService.newBoard(boardDTO);
    }
}
