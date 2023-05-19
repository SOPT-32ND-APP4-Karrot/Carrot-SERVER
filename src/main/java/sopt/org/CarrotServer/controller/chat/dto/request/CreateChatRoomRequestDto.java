package sopt.org.CarrotServer.controller.chat.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateChatRoomRequestDto {

    private Long saleId;

    private Long userId;

    // ChatRoom 생성 시 자동으로 Review도 생성되도록 한다. (동일한 ID를 사용하기 위함)
    private Long receiverReviewContentId;
    private Long senderReviewContentId;
}
