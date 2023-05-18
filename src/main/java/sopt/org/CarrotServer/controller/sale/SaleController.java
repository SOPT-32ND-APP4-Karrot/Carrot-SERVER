package sopt.org.CarrotServer.controller.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.sale.dto.request.CreateSaleRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleDetailResponseDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleResponseDto;
import sopt.org.CarrotServer.exception.SuccessStatus;
import sopt.org.CarrotServer.service.sale.SaleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    //상품 생성
    @PostMapping("")
    public ApiResponse<Long> createSale(@RequestBody final CreateSaleRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_SALE_SUCCESS, saleService.createSale(request));
    }

    //[GET] 홈_전체 상품 조회
    @GetMapping("")
    public ApiResponse<List<SaleResponseDto>> getSales() {
        return ApiResponse.success(SuccessStatus.READ_ALL_SALE_SUCCESS, saleService.getSales());
    }

    //[GET] 상세_상품 상세 조회
    @GetMapping("/{saleId}")
    public ApiResponse<SaleDetailResponseDto> getSaleById(@PathVariable final Long saleId) {
        return ApiResponse.success(SuccessStatus.READ_SALE_DETAIL_SUCCESS, saleService.getSaleById(saleId));
    }


}
