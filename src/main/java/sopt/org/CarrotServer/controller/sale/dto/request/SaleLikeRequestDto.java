package sopt.org.CarrotServer.controller.sale.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleLikeRequestDto {

    private Long saleId;  // Optional
    private Long userId;

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
