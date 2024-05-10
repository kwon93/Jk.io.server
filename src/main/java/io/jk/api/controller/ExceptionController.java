package io.jk.api.controller;


import com.sun.jdi.request.ExceptionRequest;
import io.jk.api.exception.ExceptionResponse;
import io.jk.api.exception.JkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse methodArgsHandler(MethodArgumentNotValidException e){
        ExceptionResponse response = ExceptionResponse.builder()
                .timeStamp(LocalDateTime.now())
                .httpCode("400")
                .errorMessage("잘못된 요청입니다. API 문서 확인 후 재요청 바랍니다.")
                .userMessage("잘못된 요청입니다. 다시 확인 후 입력해주세요!")
                .build();
        for (FieldError fieldError: e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }

    @ResponseBody
    @ExceptionHandler(JkException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(JkException e){
        ExceptionResponse response = ExceptionResponse.builder()
                .httpCode(String.valueOf(e.statusCode()))
                .errorMessage(e.getMessage())
                .userMessage(e.getUserMessage())
                .valid(e.getValid())
                .build();

        return ResponseEntity.status(e.statusCode()).body(response);
    }



}
