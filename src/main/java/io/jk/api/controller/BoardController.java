package io.jk.api.controller;

import io.jk.api.controller.dto.BoardPageDto;
import io.jk.api.controller.dto.CreateBoardDto;
import io.jk.api.controller.dto.UpdateBoardRequest;
import io.jk.api.service.BoardService;
import io.jk.api.service.dto.BoardListByPaged;
import io.jk.api.service.dto.OneBoardResponse;
import io.jk.api.service.dto.UpdatedBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("board")
    public ResponseEntity<Void> createBoard(@RequestBody CreateBoardDto createBoardDto){
        boardService.createBoard(createBoardDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("board/{boardId}")
    public ResponseEntity<OneBoardResponse> getBoard(@PathVariable("boardId") Long boardId){
        OneBoardResponse response = boardService.getBoard(boardId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("board")
    public ResponseEntity<BoardListByPaged> getPagedBoard(@ModelAttribute BoardPageDto boardPageDto){
        BoardListByPaged response = boardService.getPage(boardPageDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("board/{boardId}")
    public ResponseEntity<UpdatedBoardDto> updateBoard(@PathVariable("boardId") Long boardId, @RequestBody UpdateBoardRequest updateBoardRequest){
        UpdatedBoardDto response = boardService.update(updateBoardRequest, boardId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("board/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.delete(boardId);
        return ResponseEntity.noContent().build();
    }

}
