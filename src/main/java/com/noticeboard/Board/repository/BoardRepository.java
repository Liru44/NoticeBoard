package com.noticeboard.Board.repository;

import com.noticeboard.Board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    public void newBoard(BoardDTO boardDTO) {
        sqlSessionTemplate.insert("Board.save", boardDTO);
    }
}
