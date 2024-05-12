package io.jk.api.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardListByPaged {

    private List<OneBoardResponse> boards;

    @Builder
    public BoardListByPaged(List<OneBoardResponse> boards) {
        this.boards = boards;
    }

    public static BoardListByPaged of(List<OneBoardResponse> boards){
        return BoardListByPaged.builder()
                .boards(boards)
                .build();
    }
}
