package sopt.org.CarrotServer.exception.model;

import sopt.org.CarrotServer.exception.ErrorStatus;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorStatus error, String message) {
        super(error, message);
    }
}
