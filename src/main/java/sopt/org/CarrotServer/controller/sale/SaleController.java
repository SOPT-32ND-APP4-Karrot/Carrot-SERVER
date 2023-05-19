package sopt.org.CarrotServer.controller.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.sale.dto.request.CreateSaleRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.*;
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
    public ApiResponse<Long> createSale(@ModelAttribute final CreateSaleRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_SALE_SUCCESS, saleService.createSale(request));
    }

    //[GET] 홈_전체 상품 조회
    @GetMapping("")
    public ApiResponse<List<SaleResponseDto>> getSales() {
        return ApiResponse.success(SuccessStatus.READ_ALL_SALE_SUCCESS, saleService.getSales());
    }

    //[GET] 상세_상품 상세 조회
    @GetMapping("/{saleId}")
    public ApiResponse<SaleDetailResponseDto> getSaleById(@PathVariable("saleId") final Long saleId) {
        return ApiResponse.success(SuccessStatus.READ_SALE_DETAIL_SUCCESS, saleService.getSaleById(saleId));
    }

    //[GET] 상세_광고 조회
    @GetMapping("/advertisement")
    public ApiResponse<SaleSimpleResponseDto> getRandomSale() {
        return ApiResponse.success(SuccessStatus.READ_RANDOM_SALE_SUCCESS, saleService.getRandomSale());
    }

    //[GET] 상세_판매 상품 조회
    @GetMapping("/user/{userId}")
    public ApiResponse<SellerSaleResponseDto> getSaleByUserId(@PathVariable(value = "userId") final Long userId) {
        return ApiResponse.success(SuccessStatus.READ_SELLER_SALE_SUCCESS, saleService.getSaleByUserId(userId));
    }

    //[GET] 상세_함께 본 상품 조회
    @GetMapping("/{saleId}/recommendation")
    public ApiResponse<List<SaleInfoDto>> getSaleByCategory(@PathVariable("saleId") final Long saleId) {
        return ApiResponse.success(SuccessStatus.READ_RECOMMENDATION_SALE_SUCCESS, saleService.getSaleByCategory(saleId));
    }


}
