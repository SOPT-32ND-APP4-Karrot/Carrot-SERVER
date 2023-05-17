package sopt.org.CarrotServer.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewRequestDto;
import sopt.org.CarrotServer.controller.review.dto.request.ReviewContentRequestDto;
import sopt.org.CarrotServer.domain.review.Review;
import sopt.org.CarrotServer.service.review.ReviewContentService;
import sopt.org.CarrotServer.service.review.ReviewService;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewContentService reviewContentService;

    @PostMapping
    public Long createReview(@RequestBody CreateReviewRequestDto request) {
        return reviewService.createReview(request);
    }

    @PostMapping("/content")
    public Long createReviewContent(@RequestBody ReviewContentRequestDto request) {
        return reviewContentService.createReviewContent(request);
    }
}
