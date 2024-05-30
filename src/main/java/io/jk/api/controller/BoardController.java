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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController(value = "api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("board")
    public ResponseEntity<Void> createBoard(@RequestBody @Validated CreateBoardDto createBoardDto){
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("board/{boardId}")
    public ResponseEntity<UpdatedBoardDto> updateBoard(@PathVariable("boardId") Long boardId, @RequestBody @Validated UpdateBoardRequest updateBoardRequest){
        UpdatedBoardDto response = boardService.update(updateBoardRequest, boardId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("board/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.delete(boardId);
        return ResponseEntity.noContent().build();
    }

}
