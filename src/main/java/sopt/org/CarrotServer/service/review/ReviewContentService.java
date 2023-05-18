package sopt.org.CarrotServer.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewContentRequestDto;
import sopt.org.CarrotServer.controller.review.dto.response.ReviewContentResponseDto;
import sopt.org.CarrotServer.domain.review.ReviewCategory;
import sopt.org.CarrotServer.domain.review.ReviewContent;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.infrastructure.review.ReviewContentRepository;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

import static sopt.org.CarrotServer.exception.ErrorStatus.NO_EXISTS_REVIEW;

@Service
@RequiredArgsConstructor
public class ReviewContentService {

    private final ReviewContentRepository reviewContentRepository;
    private final UserRepository userRepository;

    /**
     * 후기 내용 생성하기
     * @param request
     * @return
     */
    @Transactional
    public ReviewContentResponseDto createReviewContent(CreateReviewContentRequestDto request) {
        User writer = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_USER)
        );

        ReviewContent reviewContent = ReviewContent.builder()
                .content(ReviewCategory.nameOf(request.getContent()))
                .writer(writer)
                .build();
        reviewContentRepository.save(reviewContent);

        reviewContent.setWriter(writer);

        return ReviewContentResponseDto.of(reviewContent);

    }

    /**
     * 후기 내용 ID로 조회하기
     * @param reviewContentId
     * @return
     */
    public ReviewContent findReviewContent(Long reviewContentId) {
        return reviewContentRepository.findById(reviewContentId).orElseThrow(
                () -> new CustomException(NO_EXISTS_REVIEW)
        );
    }
}
