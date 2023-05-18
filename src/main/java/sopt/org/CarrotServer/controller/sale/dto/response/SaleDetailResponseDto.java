package sopt.org.CarrotServer.controller.sale.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.controller.user.dto.response.UserDetailResponseDto;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.user.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleDetailResponseDto {

    private SaleDetailDto sale;
    private UserDetailResponseDto user;
    private Long chatRoomId;

    public static SaleDetailResponseDto of(Sale sale, Integer likeCount, Boolean isCheckLike, User user, Long chatRoomId) {
        return new SaleDetailResponseDto(
                SaleDetailDto.of(sale, likeCount, isCheckLike),
                UserDetailResponseDto.of(user),
                chatRoomId
        );
    }
}


