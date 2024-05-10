package io.jk.api.repository;

import io.jk.api.controller.dto.CreateBoardDto;
import io.jk.api.domain.Board;
import io.jk.api.service.dto.OneBoardResponse;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void createBoard(Board request){
        sqlSessionTemplate.insert("Board-mapper.createBoard", request);
    };

    public Board getBoard(Long boardId) {
        return sqlSessionTemplate.selectOne("Board-mapper.getBoard", boardId);
    }
}
