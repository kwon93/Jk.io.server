package io.jk.api.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {

    private LocalDateTime timeStamp;
    private String httpCode;
    private String errorMessage;
    private String userMessage;
    private Map<String , String > valid = new HashMap<>();

    @Builder
    public ExceptionResponse(LocalDateTime timeStamp, String httpCode, String errorMessage, String userMessage, Map<String, String> valid) {
        this.timeStamp = timeStamp;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
        this.userMessage = userMessage;
        this.valid = valid != null ? valid : new HashMap<>();
    }

    public void addValidation(String field, String defaultMessage){
        valid.put(field, defaultMessage);
    }
}
