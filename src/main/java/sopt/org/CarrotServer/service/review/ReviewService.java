package sopt.org.CarrotServer.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewRequestDto;
import sopt.org.CarrotServer.controller.review.dto.response.ReviewResponseDto;
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
    private final ReviewContentService reviewContentService;

    /**
     * 후기 생성하기
     * @param request
     * @return
     */
    @Transactional
    public ReviewResponseDto createReview(CreateReviewRequestDto request) {

        ReviewContent receiverReviewContent = reviewContentService.findReviewContent(request.getReceiverReviewContentId());
        ReviewContent senderReviewContent = reviewContentService.findReviewContent(request.getSenderReviewContentId());

        Review review = Review.builder()
                .receiverReviewContent(receiverReviewContent)
                .senderReviewContent(senderReviewContent)
                .build();

        reviewRepository.save(review);

        review.setReceiverReviewContent(receiverReviewContent);
        review.setSenderReviewContent(senderReviewContent);

        return ReviewResponseDto.of(review);
    }

    /**
     * 리뷰 ID로 단건 조회
     * @param reviewId
     * @return
     */
    public ReviewResponseDto getReviewById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new CustomException(NO_EXISTS_REVIEW)
        );

        return ReviewResponseDto.of(review);
    }


}
