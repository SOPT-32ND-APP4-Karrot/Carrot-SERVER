package sopt.org.CarrotServer.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.controller.review.dto.request.CreateReviewContentRequestDto;
import sopt.org.CarrotServer.controller.review.dto.response.ReviewContentResponseDto;
import sopt.org.CarrotServer.domain.review.ReviewCategory;
import sopt.org.CarrotServer.domain.review.ReviewContent;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.NotFoundException;
import sopt.org.CarrotServer.infrastructure.review.ReviewContentRepository;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_USER)
        );

        List<ReviewCategory> reviewCategoryList = new ArrayList<>();
        log.info("reviewCategoryList 생성 전! " + reviewCategoryList.size());
        log.info("reviewcontent 생성 시 받아온 String list: " + request.getContent());
        for (String reviewContent : request.getContent()) {
            reviewCategoryList.add(ReviewCategory.nameOf(reviewContent));
        }
        log.info("reviewCategoryList 생성! " + reviewCategoryList.size());

        ReviewContent reviewContent = ReviewContent.builder()
                .content(reviewCategoryList)
                .writer(writer)
                .build();
        log.info("reviewContent.getContent(): " + reviewContent.getContent());
        reviewContent.getContent().addAll(reviewCategoryList);
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
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_REVIEW)
        );
    }
}
