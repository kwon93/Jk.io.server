package io.jk.api.exception;

public class SignupFailException extends JkException {

    private static String MESSAGE = "회원가입이 실패되었습니다.";
    private static String USER_MESSAGE = "회원가입이 실패되었습니다.";

    public SignupFailException() {
        super(MESSAGE, USER_MESSAGE);
    }

    public SignupFailException(Throwable cause) {
        super(MESSAGE, cause, USER_MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
