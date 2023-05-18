package sopt.org.CarrotServer.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public ApiResponse<ReviewResponseDto> createReview(@RequestBody final CreateReviewRequestDto request) {
        try {
            return ApiResponse.success(SuccessStatus.CREATE_REVIEW_SUCCESS, reviewService.createReview(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }

    @PostMapping("/content")
    public ApiResponse<ReviewContentResponseDto> createReviewContent(@RequestBody final CreateReviewContentRequestDto request) {
        try {
            return ApiResponse.success(SuccessStatus.CREATE_REVIEW_CONTENT_SUCCESS, reviewContentService.createReviewContent(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }

    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponseDto> getReview(@PathVariable("reviewId") final Long reviewId) {
        try {
            return ApiResponse.success(SuccessStatus.GET_REVIEW_SUCCESS, reviewService.getReviewById(reviewId));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }
}
