package com.noticeboard.Board.dto;

import java.time.LocalDateTime;

public class ReplyDTO {
    private String content;
    private String originator;
    private LocalDateTime originate;
    private Long board_id; // 외래키 (게시글 ID)

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public LocalDateTime getOriginate() {
        return originate;
    }

    public void setOriginate(LocalDateTime originate) {
        this.originate = originate;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }
}
