package io.jk.api.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatedBoardDto {

    private String updatedTitle;
    private String updatedContent;

    @Builder
    public UpdatedBoardDto(String updatedTitle, String updatedContent) {
        this.updatedTitle = updatedTitle;
        this.updatedContent = updatedContent;
    }
}
