package io.jk.api.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBoardDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private String category;

    @Builder
    public CreateBoardDto(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category == null? "GENERAL" : category;
    }


}
