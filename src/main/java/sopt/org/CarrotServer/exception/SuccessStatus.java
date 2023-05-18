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
    GET_CHATROOM_INFO_SUCCESS(HttpStatus.OK, "채팅방 정보 조회에 성공했습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
