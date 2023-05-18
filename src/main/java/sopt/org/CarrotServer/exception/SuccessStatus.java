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
    CREATE_CHAT_MESSAGE_SUCCESS(HttpStatus.CREATED, "채팅방 메시지 생성에 성공했습니다."),
    CREATE_CHAT_ROOM_SUCCESS(HttpStatus.CREATED, "채팅방 생성에 성공했습니다."),

    // User 관련
    CREATE_USER_SUCCESS(HttpStatus.CREATED, "유저 생성 성공"),
    CREATE_SALE_SUCCESS(HttpStatus.CREATED, "상품 생성 성공"),

    // Sale 관련
    READ_ALL_SALE_SUCCESS(HttpStatus.OK, "전체 상품 조회 성공"),

    // Review 관련
    CREATE_REVIEW_SUCCESS(HttpStatus.CREATED, "후가 생성에 성공했습니다."),
    CREATE_REVIEW_CONTENT_SUCCESS(HttpStatus.CREATED, "후기내용 생성에 성공했습니다.");



    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
