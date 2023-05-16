package sopt.org.CarrotServer.controller.review.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReviewContentResponseDto {

    private Long receiverReviewContentId;
    private Long senderReviewContentId;

}
