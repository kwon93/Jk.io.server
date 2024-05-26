package io.jk.api.exception;

public class LoginFailException extends JkException {

    private static String MESSAGE = "로그인이 실패되었습니다.";
    private static String USER_MESSAGE = "로그인이 실패되었습니다.";

    public LoginFailException() {
        super(MESSAGE, USER_MESSAGE);
    }

    public LoginFailException(Throwable cause) {
        super(MESSAGE, cause, USER_MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
