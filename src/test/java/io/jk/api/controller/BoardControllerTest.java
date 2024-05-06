package io.jk.api.controller;

import io.jk.api.ControllerTestSupport;
import io.jk.api.controller.dto.CreateBoardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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


}