package sopt.org.CarrotServer.controller.sale.dto.response;

import sopt.org.CarrotServer.domain.sale.Sale;

public class SaleResponseDto {

    public static SaleResponseDto of(Sale sale) {
        return new SaleResponseDto();
    }
}
