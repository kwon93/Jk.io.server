package io.jk.api.service.dto;

import io.jk.api.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OneBoardResponse {

    private Long boardId;
    private String title;
    private String content;
    private String writerNickName;
    private Long viewCount;
    private String category;

    @Builder
    public OneBoardResponse(Long boardId, String title, String content, String writerNickName, Long viewCount, String category) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerNickName = writerNickName;
        this.viewCount = viewCount;
        this.category = category;
    }

    public static OneBoardResponse of(Board entity){
        return OneBoardResponse.builder()
                .boardId(entity.getBoardId())
                .viewCount(entity.getViewCount())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();

    }
}
