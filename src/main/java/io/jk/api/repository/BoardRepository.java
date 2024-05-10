package io.jk.api.repository;

import io.jk.api.controller.dto.CreateBoardDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void createBoard(CreateBoardDto createBoardDto){
        sqlSessionTemplate.insert("Board.createBoard", createBoardDto);
    };
}
