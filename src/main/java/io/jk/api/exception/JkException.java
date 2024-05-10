package io.jk.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class JkException extends RuntimeException{
    private Map<String ,String > valid = new HashMap<>();
    private String userMessage;

    public JkException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
    }

    public JkException(String message, Throwable cause, String userMessage) {
        super(message, cause);
        this.userMessage = userMessage;
    }

    public abstract int statusCode();

    public void addValid(String fieldName, String message){
        valid.put(fieldName,message);
    }
}
