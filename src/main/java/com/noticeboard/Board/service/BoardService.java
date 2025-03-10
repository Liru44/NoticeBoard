package com.noticeboard.Board.service;

import com.noticeboard.Board.dto.BoardDTO;
import com.noticeboard.Board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void newBoard(BoardDTO boardDTO) {
        boardRepository.newBoard(boardDTO);
    }

    public List<BoardDTO> getBoardList(int page, int defaultBoardCount) {
        return boardRepository.getBoardList(page, defaultBoardCount);
    }

    public void updateViews(Long id) {
        boardRepository.updateViews(id);
    }

    public BoardDTO getBoardInfo(Long id) {
        return boardRepository.getBoardInfo(id);
    }

    public void editBoard(BoardDTO boardDTO) {
        boardRepository.editBoard(boardDTO);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }

    public List<BoardDTO> highViewsBoard() {
        return boardRepository.highViewsBoard();
    }

    public int getBoardCount() {
        return boardRepository.getBoardCount();
    }

    public Long getBoardLastBoardId() {
        return boardRepository.getBoardLastBoardId();
    }
}
