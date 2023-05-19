package sopt.org.CarrotServer.controller.sale.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.Sale;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleDetailDto {
    private Long saleId;
    private String saleImgUrl;
    private String title;
    private String category;
    private String time;
    private Boolean isUpdated;
    private Integer likeCount;
    private Integer viewCount;
    private String description;
    private Boolean isCheckLike;
    private Integer price;

    public static SaleDetailDto of(Sale sale, Integer likeCount, Boolean isCheckLike) {
        return new SaleDetailDto(
                sale.getSaleId(),
                sale.getSaleImgUrl(),
                sale.getTitle(),
                sale.getCategory(),
                sale.getIsUpdated() ? sale.getUpdatedAt() : sale.getCreatedAt(),
                sale.getIsUpdated(),
                likeCount,
                sale.getView(),
                sale.getDescription(),
                isCheckLike,
                sale.getPrice()
        );
    }
}
