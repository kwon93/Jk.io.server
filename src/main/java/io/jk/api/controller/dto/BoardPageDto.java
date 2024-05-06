package io.jk.api.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardPageDto {

    private Integer page = 1;
    private Integer size = 10;

    @Builder
    public BoardPageDto(Integer page, Integer size) {
        this.page = page == null? 1 : page;
        this.size = size == null? 10 : size;
    }

    public int getOffset(){
        return (Math.max(1, page) -1 ) * size;
    }
}
