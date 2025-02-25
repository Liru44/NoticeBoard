package com.noticeboard.Board.service;

import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.dto.ReplyDTO;
import com.noticeboard.Board.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    private final ReplyMapper replyMapper;

    @Autowired
    public ReplyService(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public void newReply(ReplyDTO replyDTO) {
        replyMapper.newReply(replyDTO);
    }

    public List<ReplyDTO> getReplyList(Long id) {
        return replyMapper.getReplyList(id);
    }

    public void deleteReply(Long id) {
        replyMapper.deleteReply(id);
    }

    public void editReply(Long id, String content) {
        replyMapper.editReply(id, content);
    }
}
