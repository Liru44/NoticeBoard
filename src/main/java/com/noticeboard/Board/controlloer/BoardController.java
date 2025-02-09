package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.component.CustomUserDetails;
import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.service.BoardService;
import com.noticeboard.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;

    //게시글 작성
    @GetMapping("/newBoard")
    public String newBoard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("userID", userDetails.getUsername());
        } else {
            model.addAttribute("userName", null);
        }
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

        //작성자 가져오기
        if (!boardList.isEmpty()) {
            for (int i = 0; i < boardList.size(); i++) {
                BoardDTO boardDTO = boardList.get(i);
                String originatorID = boardDTO.getOriginator();
                UserDTO userDTO = userService.getUserInfo(originatorID);

                if (userDTO != null) {
                    String userName = userDTO.getName();
                    boardDTO.setOriginatorName(userName);
                } else {
                    boardDTO.setOriginatorName("");
                }
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("userName", userDetails.getName());
            String userRole = userDetails.getRole();
            model.addAttribute("role", userRole);
        } else {
            model.addAttribute("userName", null);
        }
        return "boardList";
    }

    //게시글 상제 조회
    @GetMapping("/board/{id}")
    public String getBoardInfo(@PathVariable("id") Long id, Model model) {
        //조회수 처리
        boardService.updateViews(id);

        //상세내용 조회
        BoardDTO boardDTO = boardService.getBoardInfo(id);
        model.addAttribute("board", boardDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String);
        model.addAttribute("loggedIn", isLoggedIn);
        if (isLoggedIn) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String userRole = userDetails.getRole();
            if (userDetails.getUsername().equals(boardDTO.getOriginator()) || userRole.equals("ROLE_ADMIN")) {
                model.addAttribute("originator", true);
            } else {
                model.addAttribute("originator", false);
            }
        } else {
            model.addAttribute("originator", false);
        }
        return "boardInfo";
    }

    //게시글 수정
    @GetMapping("/board/edit/{id}")
    public String editBoard(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.getBoardInfo(id);
        model.addAttribute("board", boardDTO);
        return "editBoard";
    }

    @PostMapping("/board/edit/{id}")
    public String editBoard(BoardDTO boardDTO, Model model) {
        //수정 기능
        boardService.editBoard(boardDTO);

        //게시글 다시 조회
        boardDTO = boardService.getBoardInfo(boardDTO.getId());
        model.addAttribute("board", boardDTO);
        return "redirect:/board/" + boardDTO.getId();
    }

    //게시글 삭제
    @GetMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boardList";
    }
}
