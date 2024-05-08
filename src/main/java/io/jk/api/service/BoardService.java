package io.jk.api.service;

import io.jk.api.controller.dto.BoardPageDto;
import io.jk.api.controller.dto.CreateBoardDto;
import io.jk.api.controller.dto.UpdateBoardRequest;
import io.jk.api.service.dto.BoardListByPaged;
import io.jk.api.service.dto.OneBoardResponse;
import io.jk.api.service.dto.UpdatedBoardDto;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    public OneBoardResponse getBoard(Long boardId) {
        return new OneBoardResponse();
    }

    public void createBoard(CreateBoardDto createBoardDto) {
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
