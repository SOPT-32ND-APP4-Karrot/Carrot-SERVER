package sopt.org.CarrotServer.controller.review.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.review.Review;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewResponseDto {

    private Long reviewId;

    private ReviewContentResponseDto senderReview;

    private ReviewContentResponseDto receiverReview;

    public static ReviewResponseDto of(Review review) {
        return new ReviewResponseDto(
                review.getReviewId(),
                ReviewContentResponseDto.of(review.getSenderReviewContent()),
                ReviewContentResponseDto.of(review.getReceiverReviewContent()));
    }
}
