package sopt.org.CarrotServer.controller.review.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewRequestDto {

    private Long senderReviewContentId;

    private Long receiverReviewContentId;
}
