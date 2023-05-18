package sopt.org.CarrotServer.controller.review.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReviewRequestDto {

    private Long userId;
    private Long receiverReviewContentId;
    private Long senderReviewContentId;
}
