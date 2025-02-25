package com.noticeboard.Board.repository;

import com.noticeboard.Board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    public void newBoard(BoardDTO boardDTO) {
        sqlSessionTemplate.insert("Board.newBoard", boardDTO);
    }

    public List<BoardDTO> getBoardList(int page, int defaultBoardCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", page);
        params.put("defaultBoardCount", defaultBoardCount);
        return sqlSessionTemplate.selectList("Board.getBoardList", params);
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

    public List<BoardDTO> highViewsBoard() {
        return sqlSessionTemplate.selectList("Board.highViewsBoard");
    }

    public int getBoardCount() {
        return sqlSessionTemplate.selectOne("Board.getBoardCount");
   }

   public Long getBoardLastBoardId() {
        return sqlSessionTemplate.selectOne("Board.getBoardLastBoardId");
   }
}
