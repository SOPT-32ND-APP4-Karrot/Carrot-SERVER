package sopt.org.CarrotServer.controller.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.sale.dto.request.SaleLikeRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleLikeResponseDto;
import sopt.org.CarrotServer.exception.SuccessStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.service.sale.SaleLikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sake/like")
public class SaleLikeController {

    private final SaleLikeService saleLikeService;

    @PostMapping("/{saleId}")
    public ApiResponse<SaleLikeResponseDto> likeSale(@PathVariable("saleId") final Long saleId, @RequestBody final SaleLikeRequestDto request) {

        if (saleId != null) {
            request.setSaleId(saleId);
        }

        try {
            return ApiResponse.success(SuccessStatus.SALE_LIKE_SUCCESS, saleLikeService.likeSale(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }

    @DeleteMapping("/{saleId}")
    public ApiResponse<SaleLikeResponseDto> dislikeSale(@PathVariable("saleId") final Long saleId, @RequestBody final SaleLikeRequestDto request) {

        if (saleId != null) {
            request.setSaleId(saleId);
        }

        try {
            return ApiResponse.success(SuccessStatus.SALE_DISLIKE_SUCCESS, saleLikeService.dislikeSale(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }
}
