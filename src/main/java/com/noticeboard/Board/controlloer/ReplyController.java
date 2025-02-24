package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.ReplyDTO;
import com.noticeboard.Board.service.BoardService;
import com.noticeboard.Board.service.ReplyService;
import com.noticeboard.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //댓글 삭제
    @DeleteMapping("/reply/delete")
    public ResponseEntity<String> deleteReply(@RequestParam("id") Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    //댓글 수정
    @PostMapping("/reply/edit")
    public ResponseEntity<String> editReply(@RequestParam("id") Long id, @RequestParam("content") String content) {
        replyService.editReply(id, content);
        return ResponseEntity.ok("댓글 수정 완료");
    }
}
