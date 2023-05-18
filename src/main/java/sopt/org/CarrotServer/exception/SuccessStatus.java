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

    // Chat 관련
    GET_CHATROOM_INFO_SUCCESS(HttpStatus.OK, "채팅방 정보 조회에 성공했습니다."),
    CREATE_CHAT_MESSAGE_SUCCESS(HttpStatus.OK, "채팅방 메시지 생성에 성공했습니다."),
    CREATE_CHAT_ROOM_SUCCESS(HttpStatus.OK, "채팅방 생성에 성공했습니다."),

    // User 관련
    CREATE_USER_SUCCESS(HttpStatus.CREATED, "유저 생성 성공"),

    // Sale 관련
    CREATE_SALE_SUCCESS(HttpStatus.CREATED, "상품 생성 성공"),
    READ_ALL_SALE_SUCCESS(HttpStatus.OK, "전체 상품 조회 성공"),
    READ_SALE_DETAIL_SUCCESS(HttpStatus.OK, "상품 상세 조회 성공");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
