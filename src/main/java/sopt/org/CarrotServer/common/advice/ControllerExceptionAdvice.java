package sopt.org.CarrotServer.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.exception.model.NotFoundException;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    /**
     * custom error
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiResponse handleNotFoundException(NotFoundException e) {
        return ApiResponse.error(e.getErrorStatus(), e.getMessage());
    }
}
