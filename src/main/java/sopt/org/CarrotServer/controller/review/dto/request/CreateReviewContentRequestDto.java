package sopt.org.CarrotServer.controller.review.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReviewContentRequestDto {

    private String content;

//    private Long userId;
}
