package io.jk.api.domain;

import io.jk.api.controller.dto.CreateBoardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Board {
    private Long boardId;
    private Long memberId;
    private String category;
    private String title;
    private String content;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Board(Long boardId, Long memberId, String category, String title, String content,
                 Long viewCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.boardId = boardId;
        this.memberId = memberId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Board of(CreateBoardDto createBoardDto){
        return Board.builder().
                title(createBoardDto.getTitle())
                .content(createBoardDto.getContent())
                .category(createBoardDto.getCategory())
                .build();
    }

}
