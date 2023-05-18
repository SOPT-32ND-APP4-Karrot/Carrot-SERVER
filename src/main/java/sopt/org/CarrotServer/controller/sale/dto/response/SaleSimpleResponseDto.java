package sopt.org.CarrotServer.controller.sale.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.Sale;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleSimpleResponseDto {
    private Long saleId;
    private String saleImgUrl;
    private String title;
    private String nickname;
    private Integer price;

    public static SaleSimpleResponseDto of(Sale sale) {
        return new SaleSimpleResponseDto(
                sale.getSaleId(),
                sale.getSaleImgUrl(),
                sale.getTitle(),
                sale.getUser().getNickname(),
                sale.getPrice()
        );
    }
}
