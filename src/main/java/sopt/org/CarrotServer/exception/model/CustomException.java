package sopt.org.CarrotServer.exception.model;

import lombok.Getter;
import sopt.org.CarrotServer.exception.ErrorStatus;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus error, String message) {
        super(message);
        this.errorStatus = error;
    }

    public int getHttpStatus() {
        return errorStatus.getHttpStatusCode();
    }
}
