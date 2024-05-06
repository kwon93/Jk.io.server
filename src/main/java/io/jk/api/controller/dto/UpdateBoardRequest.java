package io.jk.api.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateBoardRequest {

    private String updateTitle;
    private String updateContent;

    @Builder
    public UpdateBoardRequest(String updateTitle, String updateContent) {
        this.updateTitle = updateTitle;
        this.updateContent = updateContent;
    }
}
