package sopt.org.CarrotServer.controller.review.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReviewContentRequestDto {

    private List<String> content;

    private Long userId;
}
