package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.StringUtil;
import com.noticeboard.Board.component.CustomUserDetails;
import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.dto.ReplyDTO;
import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.service.BoardService;
import com.noticeboard.Board.service.ReplyService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    private final ReplyService replyService;

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
    public String getBoardList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        //게시글 갯수 가져오기
        int boardCount = boardService.getBoardCount();
        int defaultBoardCount = 15;

        if (Objects.nonNull(page)) {
            page = (page - 1) * 15;
        } else {
            page = 0;
        }

        List<BoardDTO> boardList = boardService.getBoardList(page, defaultBoardCount);
        model.addAttribute("boardList", boardList);

        if (boardCount > defaultBoardCount) {
            model.addAttribute("boardPage", (boardCount/15) + 1);
        }

        //조회수 높은 게시글 5개 조회
        List<BoardDTO> highViewsBoard =  boardService.highViewsBoard();
        if (!highViewsBoard.isEmpty()) {
            model.addAttribute("highViewsBoard", highViewsBoard);
        }

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

        //해당하는 게시글의 댓글 조회
        List<ReplyDTO> replyList =  replyService.getReplyList(id);
        if (!replyList.isEmpty()) {
            for (int i = 0; i < replyList.size(); i++) {
                ReplyDTO replyDTO = replyList.get(i);
                String originator = replyDTO.getOriginator();
                UserDTO userDTO = userService.getUserInfo(originator);
                if (userDTO != null) {
                    String userName = userDTO.getName();
                    replyDTO.setOriginator(userName);
                }
            }
            model.addAttribute("replyList", replyList);
        } else {
            model.addAttribute("replyList", new ArrayList<>());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String);
        model.addAttribute("loggedIn", isLoggedIn);

        //유저 정보
        if (isLoggedIn) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String userID = userDetails.getUsername();
            String userName = userDetails.getName();
            String userRole = userDetails.getRole();

            //UserID model로 넘김
            model.addAttribute("userID", userID);

            if (userRole.equals("ROLE_ADMIN")) {
                model.addAttribute("userName", "ADMIN");
            } else if (StringUtil.isNotEmpty(userName)) {
                model.addAttribute("userName", userName);
            } else {
                model.addAttribute("userName", "");
            }

            //작성자면 수정, 삭제 가능
            if (StringUtil.isNotEmpty(userID)) {
                if (userID.equals(boardDTO.getOriginator()) || userRole.equals("ROLE_ADMIN")) {
                    model.addAttribute("originator", true);
                }
            } else {
                model.addAttribute("originator", false);
            }
        } else {
            //유저가 로그인 한 상태가 아니면 userID는 공백으로 넘김
            model.addAttribute("userID", "");
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

    @GetMapping("/userinfo")
    public String getUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String) {
            return "redirect:/login";
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserDTO user = userService.getUserInfo(userDetails.getUsername());

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        return "userinfo";
    }
}
