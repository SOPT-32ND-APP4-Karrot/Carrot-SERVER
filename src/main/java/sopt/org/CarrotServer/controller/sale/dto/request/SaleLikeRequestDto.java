package sopt.org.CarrotServer.controller.sale.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleLikeRequestDto {

    private Long saleId;  // Optional
    private Long userId;

    public void setSaleId(Long saleId) {  // Path Variable 과 Request Body 방식 모두 가능하도록
        this.saleId = saleId;
    }
}
