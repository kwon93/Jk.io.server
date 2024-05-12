package io.jk.api.service;

import io.jk.api.controller.dto.BoardPageDto;
import io.jk.api.controller.dto.CreateBoardDto;
import io.jk.api.controller.dto.UpdateBoardRequest;
import io.jk.api.domain.Board;
import io.jk.api.repository.BoardRepository;
import io.jk.api.service.dto.BoardListByPaged;
import io.jk.api.service.dto.OneBoardResponse;
import io.jk.api.service.dto.UpdatedBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public OneBoardResponse getBoard(Long boardId) {
        Board board = boardRepository.getBoard(boardId);
        return OneBoardResponse.of(board);
    }

    @Transactional
    public void createBoard(CreateBoardDto createBoardDto) {
        boardRepository.createBoard(Board.of(createBoardDto));
    }

    public BoardListByPaged getPage(BoardPageDto boardPageDto) {
        List<OneBoardResponse> pages = boardRepository.getPage(boardPageDto);
        return BoardListByPaged.of(pages);
    }

    @Transactional
    public UpdatedBoardDto update(UpdateBoardRequest updateBoardRequest, Long boardId) {
        Board board = Board.fromUpdateDto(updateBoardRequest, boardId);
        boardRepository.updateBoard(board);
        Board updatedBoard = boardRepository.getBoard(board.getBoardId());
        return UpdatedBoardDto.builder()
                .updatedTitle(updatedBoard.getTitle())
                .updatedContent(updatedBoard.getContent())
                .build();
    }

    @Transactional
    public void delete(Long boardId) {
        boardRepository.deleteBoard(boardId);
    }
}
