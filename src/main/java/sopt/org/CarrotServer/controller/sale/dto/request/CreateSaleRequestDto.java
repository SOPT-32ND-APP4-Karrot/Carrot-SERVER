package sopt.org.CarrotServer.controller.sale.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateSaleRequestDto {
    private String category;
    private Integer view;
    private String title;
    private String description;
    private String saleImgUrl;
    private Boolean isUpdated;
    private Integer price;
    private Boolean isSuggest;
    private String status;
    private Boolean isDiscount;
    private Integer userId;
}
