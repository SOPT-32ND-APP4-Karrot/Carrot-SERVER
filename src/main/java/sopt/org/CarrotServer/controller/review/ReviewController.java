package sopt.org.CarrotServer.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewRequestDto;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewContentRequestDto;
import sopt.org.CarrotServer.controller.review.dto.response.ReviewContentResponseDto;
import sopt.org.CarrotServer.controller.review.dto.response.ReviewResponseDto;
import sopt.org.CarrotServer.exception.SuccessStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.service.review.ReviewContentService;
import sopt.org.CarrotServer.service.review.ReviewService;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewContentService reviewContentService;

    @PostMapping
    public ApiResponse<ReviewResponseDto> createReview(@RequestBody CreateReviewRequestDto request) {
        try {
            return ApiResponse.success(SuccessStatus.CREATE_REVIEW_SUCCESS, reviewService.createReview(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }

    @PostMapping("/content")
    public ApiResponse<ReviewContentResponseDto> createReviewContent(@RequestBody CreateReviewContentRequestDto request) {
        try {
            return ApiResponse.success(SuccessStatus.CREATE_REVIEW_CONTENT_SUCCESS, reviewContentService.createReviewContent(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }
}
