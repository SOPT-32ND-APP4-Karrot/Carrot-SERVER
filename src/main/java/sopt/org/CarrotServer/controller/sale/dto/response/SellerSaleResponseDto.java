package sopt.org.CarrotServer.controller.sale.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerSaleResponseDto {
    private String nickname;
    private List<SaleInfoDto> saleList;

    public static SellerSaleResponseDto of(User user) {
        return new SellerSaleResponseDto(
                user.getNickname(),
                user.getSales().stream().map(SaleInfoDto::of).collect(Collectors.toList())
        );
    }
}
