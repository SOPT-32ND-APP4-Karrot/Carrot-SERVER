package sopt.org.CarrotServer.controller.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.sale.dto.request.SaleLikeRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleLikeResponseDto;
import sopt.org.CarrotServer.exception.SuccessStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.service.sale.SaleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale/like")
public class SaleLikeController {

    private final SaleService saleService;


    // 상품 좋아요 체크
    @PostMapping("/{saleId}")
    public ApiResponse<SaleLikeResponseDto> likeSale(@PathVariable("saleId") final Long saleId, @RequestBody final SaleLikeRequestDto request) {

        if (saleId != null) {
            request.setSaleId(saleId);
        }

        try {
            return ApiResponse.success(SuccessStatus.SALE_LIKE_SUCCESS, saleService.likeSale(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }

    // 상품 좋아요 취소
    @DeleteMapping("/{saleId}")
    public ApiResponse<SaleLikeResponseDto> dislikeSale(@PathVariable("saleId") final Long saleId, @RequestBody final SaleLikeRequestDto request) {

        if (saleId != null) {
            request.setSaleId(saleId);
        }

        try {
            return ApiResponse.success(SuccessStatus.SALE_DISLIKE_SUCCESS, saleService.dislikeSale(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }
}
