package sopt.org.CarrotServer.controller.sale.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.Sale;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleChatRoomResponseDto {

    private Long saleId;
    private String title;
    private String saleImgUrl;
    private Integer price;
    private Boolean isSuggest;
    private String status;

    public static SaleChatRoomResponseDto of(Sale sale) {
        return new SaleChatRoomResponseDto(
                sale.getSaleId(),
                sale.getTitle(),
                sale.getSaleImgUrl(),
                sale.getPrice(),
                sale.getIsSuggest(),
                sale.getStatus().getStatus()
        );
    }
}
