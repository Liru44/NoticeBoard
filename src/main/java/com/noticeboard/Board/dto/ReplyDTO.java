package com.noticeboard.Board.dto;

import java.time.LocalDateTime;

public class ReplyDTO {
    private String content;
    private String originator;
    private String originate;
    private Long boardID; // 외래키 (게시글 ID)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getOriginate() {
        return originate;
    }

    public void setOriginate(String originate) {
        this.originate = originate;
    }

    public Long getBoardID() {
        return boardID;
    }

    public void setBoardID(Long boardID) {
        this.boardID = boardID;
    }
}
