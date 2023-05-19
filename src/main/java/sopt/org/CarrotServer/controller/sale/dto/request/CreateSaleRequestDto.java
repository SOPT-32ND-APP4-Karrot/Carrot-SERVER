package sopt.org.CarrotServer.controller.sale.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateSaleRequestDto {
    private String category;
    private Integer view;
    private String title;
    private String description;
    private Boolean isUpdated;
    private Integer price;
    private Boolean isSuggest;
    private String status;
    private Boolean isDiscount;
    private Long userId;
    private MultipartFile saleImgUrl;
}
