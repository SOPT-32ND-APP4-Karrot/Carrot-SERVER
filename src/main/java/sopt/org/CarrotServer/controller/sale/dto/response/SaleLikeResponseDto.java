package sopt.org.CarrotServer.controller.sale.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.org.CarrotServer.domain.sale.SaleLike;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleLikeResponseDto {

    private Long saleId;

    private Boolean isCheckLike;

    private Integer likeCount;

    public static SaleLikeResponseDto of(SaleLike saleLike, boolean isCheckLike) {
        return SaleLikeResponseDto.builder()
                .saleId(saleLike.getSale().getSaleId())
                .isCheckLike(isCheckLike)
                .build();
    }
}
