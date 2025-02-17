package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.ReplyDTO;
import com.noticeboard.Board.service.BoardService;
import com.noticeboard.Board.service.ReplyService;
import com.noticeboard.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyController {
    private final BoardService boardService;
    private final ReplyService replyService;
    private final UserService userService;

    //댓글 작성
    @PostMapping("/reply")
    public String newReply(@RequestParam("board_id") Long id, ReplyDTO replyDTO) {
        replyService.newReply(replyDTO);

        return "redirect:/board/" + id;
    }
}
