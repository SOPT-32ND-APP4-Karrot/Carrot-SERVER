package sopt.org.CarrotServer.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {

    /**
     * 200 OK
     */
    SALE_LIKE_SUCCESS(HttpStatus.OK, "상품의 좋아요 체크에 성공했습니다."),
    SALE_DISLIKE_SUCCESS(HttpStatus.OK, "상품의 좋아요 취소에 성공했습니다."),

    CREATE_USER_SUCCESS(HttpStatus.CREATED, "유저 생성 성공"),
    CREATE_SALE_SUCCESS(HttpStatus.CREATED, "상품 생성 성공"),
    READ_ALL_SALE_SUCCESS(HttpStatus.OK, "전체 상품 조회 성공");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
