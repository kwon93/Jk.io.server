package io.jk.api.service.dto;

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

    @Builder
    public OneBoardResponse(Long boardId, String title, String content, String writerNickName, Long viewCount) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerNickName = writerNickName;
        this.viewCount = viewCount;
    }
}
