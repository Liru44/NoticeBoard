package com.noticeboard.Board.mapper;

import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    void newReply(ReplyDTO replyDTO);

    List<ReplyDTO> getReplyList(Long id);

    void deleteReply(Long id);

    void editReply(Long id, String content);
}
