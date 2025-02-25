package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.ReplyDTO;
import com.noticeboard.Board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyController {
    private final ReplyService replyService;

    //댓글 작성
    @PostMapping("/reply")
    public String newReply(@RequestParam("boardID") Long id, ReplyDTO replyDTO) {
        replyService.newReply(replyDTO);

        return "redirect:/board/" + id;
    }

    //댓글 삭제
    @GetMapping("/board/reply/delete/{id}/{boardID}")
    public String deleteReply(@PathVariable("id") Long id, @PathVariable("boardID") Long boardID) {
        replyService.deleteReply(id);
        return "redirect:/board/" + boardID;
    }

    //댓글 수정
    @PostMapping("/reply/edit")
    public ResponseEntity<String> editReply(@RequestParam("id") Long id, @RequestParam("content") String content) {
        replyService.editReply(id, content);
        return ResponseEntity.ok("댓글 수정 완료");
    }
}
