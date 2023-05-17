package sopt.org.CarrotServer.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.CarrotServer.controller.review.dto.request.ReviewContentRequestDto;
import sopt.org.CarrotServer.domain.review.ReviewContent;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.infrastructure.review.ReviewContentRepository;

import static sopt.org.CarrotServer.exception.ErrorStatus.NO_EXISTS_REVIEW;

@Service
@RequiredArgsConstructor
public class ReviewContentService {

    private final ReviewContentRepository reviewContentRepository;

    public Long createReviewContent(ReviewContentRequestDto request) {
        ReviewContent reviewContent = ReviewContent.builder()
                .content(request.getContent())
                .build();
        reviewContentRepository.save(reviewContent);
        return reviewContent.getReviewContentId();

    }

    public ReviewContent findReviewContent(Long reviewContentId) {
        return reviewContentRepository.findById(reviewContentId).orElseThrow(
                () -> new CustomException(NO_EXISTS_REVIEW, NO_EXISTS_REVIEW.getMessage())
        );
    }
}