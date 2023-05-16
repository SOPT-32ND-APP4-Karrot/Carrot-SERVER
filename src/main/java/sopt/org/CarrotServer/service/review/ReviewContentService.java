package sopt.org.CarrotServer.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.CarrotServer.controller.review.dto.request.ReviewContentRequestDto;
import sopt.org.CarrotServer.controller.review.dto.response.CreateReviewContentResponseDto;
import sopt.org.CarrotServer.domain.review.ReviewContent;
import sopt.org.CarrotServer.infrastructure.review.ReviewContentRepository;

@Service
@RequiredArgsConstructor
public class ReviewContentService {

    private final ReviewContentRepository reviewContentRepository;

    public Long createReviewContent(ReviewContentRequestDto request) {
        ReviewContent reviewContent = ReviewContent.builder()
                .content(request.getContent())
                .build();
        return reviewContent.getReviewContentId();

    }
}
