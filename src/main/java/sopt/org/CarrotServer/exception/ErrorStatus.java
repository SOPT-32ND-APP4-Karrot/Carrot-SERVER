package sopt.org.CarrotServer.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {

    /**
     * 400 Bad Request
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 입력되지 않았습니다."),


    /**
     * 404 Not Found
     */
    NO_EXISTS_SALE(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
    NO_EXISTS_USER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    NO_EXISTS_REVIEW(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),
    NO_EXISTS_CHATROOM(HttpStatus.NOT_FOUND, "존재하지 않는 채팅방입니다."),

    /**
     * 500 Internal Server Error
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다."),
    BAD_GATEWAY_EXCEPTION(HttpStatus.BAD_GATEWAY, "일시적인 에러가 발생했습니다.\n잠시 후 다시 시도해주세요!"),
    SERVICE_UNAVAILABLE_EXCEPTION(HttpStatus.SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요!"),

    FAILED_TO_GET_CHATROOM(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스에서 채팅방을 찾는 데 실패했습니다."),
    FAILED_TO_GET_SALE(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스에서 상품을 찾는 데 실패했습니다."),
    FAILED_TO_GET_REVIEW(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스에서 리뷰를 찾는 데 실패했습니다."),
    FAILED_TO_GET_SALE_LIKE(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스에서 좋아요를 찾는 데 실패했습니다."),
    FAILED_TO_CREATE_SALE_LIKE(HttpStatus.INTERNAL_SERVER_ERROR, "좋아요 생성을 위한 유저, 상품 조회에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
