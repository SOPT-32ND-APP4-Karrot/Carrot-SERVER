package sopt.org.CarrotServer.service.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.CarrotServer.controller.sale.dto.request.SaleLikeRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleLikeResponseDto;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.sale.SaleLike;
import sopt.org.CarrotServer.domain.sale.SaleLikeId;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.infrastructure.sale.SaleLikeRepository;
import sopt.org.CarrotServer.infrastructure.sale.SaleRepository;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

@Service
@RequiredArgsConstructor
public class SaleLikeService {

    private final SaleLikeRepository saleLikeRepository;
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;

    public SaleLikeResponseDto likeSale(SaleLikeRequestDto request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_USER)
        );

        Sale sale = saleRepository.findById(request.getSaleId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_SALE)
        );

        SaleLike saleLike = SaleLike.builder()
                .sale(sale)
                .user(user)
                .build();
        saleLike.setSale(sale);

        saleLikeRepository.save(saleLike);

        return SaleLikeResponseDto.of(saleLike, true);
    }

    public SaleLikeResponseDto dislikeSale(SaleLikeRequestDto request) {

        SaleLike saleLike = saleLikeRepository.findBySaleLikeId(SaleLikeId.builder()
                .saleId(request.getSaleId())
                .userId(request.getUserId()).build()).orElseThrow(
                () -> new CustomException(ErrorStatus.FAILED_TO_GET_SALE_LIKE)
        );

        Sale sale = saleRepository.findById(request.getSaleId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_SALE)
        );
        saleLike.deleteSale(sale);

        saleLikeRepository.remove(saleLike);
        return SaleLikeResponseDto.of(saleLike, false);
    }
}
