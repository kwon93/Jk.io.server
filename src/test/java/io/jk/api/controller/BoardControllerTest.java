package io.jk.api.controller;

import io.jk.api.ControllerTestSupport;
import io.jk.api.controller.dto.BoardPageDto;
import io.jk.api.controller.dto.CreateBoardDto;
import io.jk.api.controller.dto.UpdateBoardRequest;
import io.jk.api.service.dto.BoardListByPaged;
import io.jk.api.service.dto.OneBoardResponse;
import io.jk.api.service.dto.UpdatedBoardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

class BoardControllerTest extends ControllerTestSupport {


    @Test
    @DisplayName("createBoard(): 글 등록에 성공한다.")
    @WithMockUser
    void test1() throws Exception{
        //given
        CreateBoardDto request = CreateBoardDto.builder()
                .title("제목")
                .content("내용")
                .category("NOTICE")
                .build();


        //expected
        mockMvc.perform(RestDocumentationRequestBuilders.post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(csrf().asHeader())

                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("create-board",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        requestFields(
                                fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("게시물 제목"),
                                fieldWithPath("content")
                                        .type(JsonFieldType.STRING)
                                        .description("게시물 내용"),
                                fieldWithPath("category")
                                        .type(JsonFieldType.STRING)
                                        .description("기본값은 general Admin일 경우 Notice 선택 가능")
                        )
                        )

                );

    }


    @Test
    @DisplayName("getBoard(): 글 단건 조회에 성공한다.")
    @WithMockUser
    void test2() throws Exception {
        //given
        OneBoardResponse response = OneBoardResponse.builder()
                .boardId(1L)
                .title("제목")
                .content("내용")
                .writerNickName("작성자 닉네임")
                .viewCount(1L)
                .build();

        Mockito.when(boardService.getBoard(1L)).thenReturn(response);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/board/{boardId}",1L)
                .with(csrf().asHeader()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(
                        MockMvcRestDocumentation.document("getOne-board",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                pathParameters(parameterWithName("boardId").description("조회할 게시물 번호")),
                                responseFields(
                                        fieldWithPath("boardId")
                                                .type(JsonFieldType.NUMBER)
                                                .description("게시물 번호"),
                                        fieldWithPath("title")
                                                .type(JsonFieldType.STRING)
                                                .description("게시물 제목"),
                                        fieldWithPath("content")
                                                .type(JsonFieldType.STRING)
                                                .description("게시물 내용"),
                                        fieldWithPath("writerNickName")
                                                .type(JsonFieldType.STRING)
                                                .description("작성자 닉네임"),
                                        fieldWithPath("viewCount")
                                                .type(JsonFieldType.NUMBER)
                                                .description("조회수")
                                )
                                )

                );

        Mockito.verify(boardService, Mockito.times(1)).getBoard(1L);
    }



    @Test
    @DisplayName("getPagedBoard(): 페이징처리된 게시물 목록을 가져온다.")
    @WithMockUser
    void test3() throws Exception {
        //given

        BoardPageDto pageDto = BoardPageDto.builder()
                .page(1)
                .size(10)
                .build();

        List<OneBoardResponse> boards = LongStream.range(1, 4).mapToObj(
                board -> OneBoardResponse.builder()
                        .boardId(board)
                        .title("제목"+ board)
                        .content("내용" + board)
                        .writerNickName("작성자 닉네임" + board)
                        .viewCount(1L)
                        .build()
        ).toList();

        BoardListByPaged response = BoardListByPaged.builder()
                .boards(boards)
                .build();

        Mockito.when(boardService.getPage(Mockito.any(BoardPageDto.class))).thenReturn(response);

        //when

        mockMvc.perform(RestDocumentationRequestBuilders.get("/board?page=1&size=10")
                .param("page", "1")
                .param("size", "10")
                .with(csrf().asHeader()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("paged-board",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        queryParameters(
                                parameterWithName("page").description("해당 페이지 번호"),
                                parameterWithName("size").description("한 페이지에 가져올 게시물 개수")
                        ),
                        responseFields(
                                fieldWithPath("boards").type(JsonFieldType.ARRAY).description("게시물 목록 리스트"),
                                fieldWithPath("boards[].boardId")
                                        .type(JsonFieldType.NUMBER)
                                        .description("게시물 번호"),
                                fieldWithPath("boards[].title")
                                        .type(JsonFieldType.STRING)
                                        .description("게시물 제목"),
                                fieldWithPath("boards[].content")
                                        .type(JsonFieldType.STRING)
                                        .description("게시물 내용"),
                                fieldWithPath("boards[].writerNickName")
                                        .type(JsonFieldType.STRING)
                                        .description("작성자 닉네임"),
                                fieldWithPath("boards[].viewCount")
                                        .type(JsonFieldType.NUMBER)
                                        .description("조회수")
                        )

                ));
    }



    @Test
    @DisplayName("updateBoard(): 게시물 수정에 성공한다.")
    @WithMockUser
    void test4() throws Exception {
        //given
        UpdateBoardRequest requestDto = UpdateBoardRequest.builder()
                .updateTitle("수정될 제목")
                .updateContent("수정될 내용")
                .build();

        UpdatedBoardDto response = UpdatedBoardDto.builder()
                .updatedTitle("수정된 제목")
                .updatedContent("수정된 내용")
                .build();

        Mockito.when(boardService.update(Mockito.any(UpdateBoardRequest.class), Mockito.anyLong())).thenReturn(response);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/board/{boardId}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                .with(csrf().asHeader())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("update-board",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        pathParameters(
                        parameterWithName("boardId").description("수정할 게시물 번호")),
                        requestFields(
                                fieldWithPath("updateTitle")
                                        .type(JsonFieldType.STRING)
                                        .description("수정할 제목"),
                                fieldWithPath("updateContent")
                                        .type(JsonFieldType.STRING)
                                        .description("수정할 내용")
                        ),
                        responseFields(
                                fieldWithPath("updatedTitle")
                                        .type(JsonFieldType.STRING)
                                        .description("수정 완료된 게시물 제목"),
                                fieldWithPath("updatedContent")
                                        .type(JsonFieldType.STRING)
                                        .description("수정 완료된 게시물 내용")
                        )
                ));
    }


    @Test
    @DisplayName("deleteBoard(): 게시물 삭제에 성공한다.")
    @WithMockUser
    void test5() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/board/{boardId}",1L)
                .with(csrf().asHeader()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("delete-board",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        pathParameters(
                                parameterWithName("boardId").description("삭제할 게시물 번호")
                        )
                ));
    }



}