package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    //게시글 작성
    @GetMapping("/newBoard")
    public String newBoard() {
        return "newBoard";
    }

    @PostMapping("/newBoard")
    public String newBoard(BoardDTO boardDTO) {
        boardService.newBoard(boardDTO);
        return "redirect:/boardList";
    }

    //게시글 목록 조회
    @GetMapping("/boardList")
    public String getBoardList(Model model) {
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "boardList";
    }

    //게시글 상제 조회
    @GetMapping("/{id}")
    public String getBoardInfo(@PathVariable("id") Long id, Model model) {
        //조회수 처리
        boardService.updateViews(id);

        //상세내용 조회
        BoardDTO boardDTO = boardService.getBoardInfo(id);
        model.addAttribute("board", boardDTO);
        return "boardInfo";
    }

    //게시글 수정
    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.getBoardInfo(id);
        model.addAttribute("board", boardDTO);
        return "editBoard";
    }

    @PostMapping("/edit/{id}")
    public String editBoard(BoardDTO boardDTO, Model model) {
        //수정 기능
        boardService.editBoard(boardDTO);

        //게시글 다시 조회
        boardDTO = boardService.getBoardInfo(boardDTO.getId());
        model.addAttribute("board", boardDTO);
        return "boardInfo";
    }

    //게시글 삭제
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boardList";
    }
}
