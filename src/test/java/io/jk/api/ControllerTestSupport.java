package io.jk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jk.api.controller.BoardController;
import io.jk.api.service.BoardService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        controllers = {
                BoardController.class
        }
)
@AutoConfigureRestDocs(
        uriScheme = "https",
         uriHost = "api.jk.io", uriPort = 80
)
@ExtendWith({RestDocumentationExtension.class})
@ActiveProfiles("test")
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    protected BoardService boardService;
}
