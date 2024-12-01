package com.noticeboard.Board.repository;

import com.noticeboard.Board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    public void newBoard(BoardDTO boardDTO) {
        sqlSessionTemplate.insert("Board.newBoard", boardDTO);
    }

    public List<BoardDTO> getBoardList() {
        return sqlSessionTemplate.selectList("Board.getBoardList");
    }

    public void updateViews(Long id) {
        sqlSessionTemplate.update("Board.updateViews", id);
    }

    public BoardDTO getBoardInfo(Long id) {
        return sqlSessionTemplate.selectOne("Board.getBoardInfo", id);
    }

    public void editBoard(BoardDTO boardDTO) {
        sqlSessionTemplate.update("Board.editBoard", boardDTO);
    }

    public void deleteBoard(Long id) {
        sqlSessionTemplate.delete("Board.deleteBoard", id);
    }
}
