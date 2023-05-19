package sopt.org.CarrotServer.controller.review.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.controller.user.dto.response.UserResponseDto;
import sopt.org.CarrotServer.domain.review.ReviewCategory;
import sopt.org.CarrotServer.domain.review.ReviewContent;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewContentResponseDto {

    private Long reviewContentId;  // TODO API 명세서에서는 전부 그냥 'id'로만 되어 있는데 이거 바꿀까?

    private List<String> content;

    private UserResponseDto writer;

    public static ReviewContentResponseDto of(ReviewContent reviewContent) {
        return new ReviewContentResponseDto(
                reviewContent.getReviewContentId(),
                reviewContent.getContent().stream().map(ReviewCategory::getName).collect(Collectors.toList()),
                UserResponseDto.of(reviewContent.getWriter().getUserId(), reviewContent.getWriter().getNickname()));
    }
}
