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

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public OneBoardResponse getBoard(Long boardId) {
        Board board = boardRepository.getBoard(boardId);
        return OneBoardResponse.of(board);
    }

    public void createBoard(CreateBoardDto createBoardDto) {
        boardRepository.createBoard(Board.of(createBoardDto));
    }

    public BoardListByPaged getPage(BoardPageDto boardPageDto) {
        return new BoardListByPaged();
    }

    public UpdatedBoardDto update(UpdateBoardRequest updateBoardRequest, Long boardId) {
        return new UpdatedBoardDto();
    }

    public void delete(Long boardId) {

    }
}
