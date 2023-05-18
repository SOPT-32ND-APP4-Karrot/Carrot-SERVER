package sopt.org.CarrotServer.controller.sale.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleResponseDto {
    private Long id;
    private String title;
    private String imgUrl;
    private String location;
    private String time;
    private Boolean isUpdated;
    private Integer price;
    private Boolean isDiscount;
    private Integer likeCount;
    private Boolean isCheckLike;

    public static SaleResponseDto of(Long id, String title, String imgUrl, String location, String time,
                                     Boolean isUpdated, Integer price, Boolean isDiscount, Integer likeCount, Boolean isCheckLike) {
        return new SaleResponseDto(id, title, imgUrl, location, time, isUpdated, price, isDiscount, likeCount, isCheckLike);
    }

}
