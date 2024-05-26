package io.jk.api.exception;

public class InvalidPasswordException extends JkException {

    private static String MESSAGE = "비밀번호가 맞지않음";
    private static String USER_MESSAGE = "아이디 및 비밀번호를 다시 확인해주세요.";

    public InvalidPasswordException() {
        super(MESSAGE, USER_MESSAGE);
    }

    public InvalidPasswordException(Throwable cause) {
        super(MESSAGE, cause, USER_MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
