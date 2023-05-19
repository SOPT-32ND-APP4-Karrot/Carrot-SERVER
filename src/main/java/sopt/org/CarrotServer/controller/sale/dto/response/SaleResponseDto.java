package sopt.org.CarrotServer.controller.sale.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.Sale;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleResponseDto {
    private Long saleId;
    private String title;
    private String saleImgUrl;
    private String location;
    private String time;
    private Boolean isUpdated;
    private Integer price;
    private Boolean isDiscount;
    private Integer likeCount;
    private Boolean isCheckLike;
    private Boolean isSuggest;
    private String status;


    public SaleResponseDto(Long id, String title, String imgUrl, String location, String time, Boolean isUpdated, Integer price, Boolean isDiscount, Integer likeCount, Boolean isCheckLike) {
        this.saleId = id;
        this.title = title;
        this.saleImgUrl = imgUrl;
        this.location = location;
        this.time = time;
        this.isUpdated = isUpdated;
        this.price = price;
        this.isDiscount = isDiscount;
        this.likeCount = likeCount;
        this.isCheckLike = isCheckLike;
    }


    public static SaleResponseDto of(Long id, String title, String imgUrl, String location, String time,
                                     Boolean isUpdated, Integer price, Boolean isDiscount, Integer likeCount, Boolean isCheckLike) {
        return new SaleResponseDto(id, title, imgUrl, location, time, isUpdated, price, isDiscount, likeCount, isCheckLike);
    }

    public static SaleResponseDto of(Sale sale) {

        return SaleResponseDto.builder()
                .saleId(sale.getSaleId())
                .title(sale.getTitle())
                .saleImgUrl(sale.getSaleImgUrl())
                .price(sale.getPrice())
                .isSuggest(sale.getIsSuggest())
                .status(sale.getStatus().getStatus())
                .build();
    }

}
