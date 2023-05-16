package sopt.org.CarrotServer.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewRequestDto;
import sopt.org.CarrotServer.controller.review.dto.request.ReviewRequestDto;
import sopt.org.CarrotServer.controller.review.dto.response.CreateReviewContentResponseDto;
import sopt.org.CarrotServer.domain.review.Review;
import sopt.org.CarrotServer.domain.review.ReviewContent;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.infrastructure.review.ReviewContentRepository;
import sopt.org.CarrotServer.infrastructure.review.ReviewRepository;

import static sopt.org.CarrotServer.exception.ErrorStatus.NO_EXISTS_REVIEW;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewContentRepository reviewContentRepository;

    public Long createReview(CreateReviewRequestDto request) {

        ReviewContent receiverReviewContent = reviewContentRepository.findById(request.getReceiverReviewContentId()).orElseThrow(
                () -> new CustomException(NO_EXISTS_REVIEW, NO_EXISTS_REVIEW.getMessage())
        );
        ReviewContent senderReviewContent = reviewContentRepository.findById(request.getSenderReviewContentId()).orElseThrow(
                () -> new CustomException(NO_EXISTS_REVIEW, NO_EXISTS_REVIEW.getMessage())
        );
        Review review = Review.builder()
                .receiverReviewContent(receiverReviewContent)
                .senderReviewContent(senderReviewContent)
                .build();

        reviewRepository.save(review);
        return review.getReviewId();
    }
}
