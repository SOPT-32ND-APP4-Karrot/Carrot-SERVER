package sopt.org.CarrotServer.controller.sale.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.Sale;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleInfoDto {
    private Long saleId;
    private String saleImgUrl;
    private String title;
    private Integer price;

    public static SaleInfoDto of(Sale sale) {
        return new SaleInfoDto(
                sale.getSaleId(),
                sale.getSaleImgUrl(),
                sale.getTitle(),
                sale.getPrice()
        );
    }
}
