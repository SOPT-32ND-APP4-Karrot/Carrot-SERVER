package sopt.org.CarrotServer.controller.review.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReviewRequestDto {

    private Long userId;
    private Long receiverReviewContentId;
    private Long senderReviewContentId;
}
