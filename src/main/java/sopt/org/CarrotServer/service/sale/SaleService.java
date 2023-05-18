package sopt.org.CarrotServer.service.sale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.controller.sale.dto.request.CreateSaleRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.request.SaleLikeRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleDetailResponseDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleLikeResponseDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleResponseDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleSimpleResponseDto;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.sale.SaleLike;
import sopt.org.CarrotServer.domain.sale.SaleLikeId;
import sopt.org.CarrotServer.domain.sale.SaleStatus;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.exception.model.NotFoundException;
import sopt.org.CarrotServer.infrastructure.sale.SaleLikeRepository;
import sopt.org.CarrotServer.infrastructure.sale.SaleRepository;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final SaleLikeRepository saleLikeRepository;
    private final SaleRepository saleRepository;

    //상품 생성
    @Transactional
    public Long createSale(CreateSaleRequestDto request) {
        User user = userRepository.findById(Long.valueOf(request.getUserId())).orElseThrow();
        Sale sale = Sale.builder()
                .category(request.getCategory())
                .view(request.getView())
                .title(request.getTitle())
                .description(request.getDescription())
                .saleImgUrl(request.getSaleImgUrl())
                .isUpdated(request.getIsUpdated())
                .price(request.getPrice())
                .isSuggest(request.getIsSuggest())
                .status(SaleStatus.valueOf(request.getStatus()))
                .isDiscount(request.getIsDiscount())
                .user(user)
                .build();

        sale.setUser(user);
        saleRepository.save(sale);
        return sale.getSaleId();
    }

    //홈_전체 상품 조회
    public List<SaleResponseDto> getSales() {
        final List<SaleResponseDto> saleList = new ArrayList<>();

        for (Sale sale : saleRepository.findAll()) {
            User user = userRepository.findById(sale.getUser().getUserId())
                    .orElseThrow(() -> new NotFoundException(ErrorStatus.NO_EXISTS_USER, ErrorStatus.NO_EXISTS_USER.getMessage()));

            int likeCount = Math.toIntExact(saleLikeRepository.countBySaleLikeIdSaleId(sale.getSaleId()));

            //임시로 좋아요는 판매자가 눌렀는지 여부로 구현
            boolean isCheckLike = saleLikeRepository.existsBySaleLikeId(
                    SaleLikeId.builder().saleId(sale.getSaleId()).userId(user.getUserId()).build());

            saleList.add(SaleResponseDto.of(
                    sale.getSaleId(),
                    sale.getTitle(),
                    sale.getSaleImgUrl(),
                    user.getLocation(),
                    sale.getIsUpdated() ? sale.getModifiedAt() : sale.getCreatedAt(),
                    sale.getIsUpdated(),
                    sale.getPrice(),
                    sale.getIsDiscount(),
                    likeCount,
                    isCheckLike
            ));
        }

        return saleList;
    }

    //상세_상품 상세 조회
    public SaleDetailResponseDto getSaleById(final Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_SALE, ErrorStatus.NO_EXISTS_SALE.getMessage())
        );

        //TODO 로직이 겹치는데 어떻게 하는게 좋을지?
        User user = userRepository.findById(sale.getUser().getUserId()).orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_USER, ErrorStatus.NO_EXISTS_USER.getMessage())
        );

        //TODO 좋아요 수 계산을 sale.getSaleLikeList().size() 이렇게 해도 되는지?
        int likeCount = Math.toIntExact(saleLikeRepository.countBySaleLikeIdSaleId(sale.getSaleId()));

        //임시로 좋아요는 판매자가 눌렀는지 여부로 구현
        boolean isCheckLike = saleLikeRepository.existsBySaleLikeId(
                SaleLikeId.builder().saleId(sale.getSaleId()).userId(user.getUserId()).build());

        Long chatRoomId = (long) -1; //채팅방이 존재하지 않는 경우 -1 리턴
        if (!sale.getChatRoomList().isEmpty()) {
            //상품의 첫번째 채팅방 아이디로 고정
            chatRoomId = sale.getChatRoomList().get(0).getChatRoomId();
        }

        return SaleDetailResponseDto.of(sale, likeCount, isCheckLike, user, chatRoomId);
    }

    //[GET] 상세_광고 조회
    public SaleSimpleResponseDto getRandomSale() {
        Sale sale = saleRepository.findRandomSale().orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_SALE, ErrorStatus.NO_EXISTS_SALE.getMessage())
        );

        return SaleSimpleResponseDto.of(sale);
    }
    //[GET] 상세_판매 상품 조회

    //[GET] 상세_함께 본 상품 조회


    // 상품 좋아요 체크
    @Transactional
    public SaleLikeResponseDto likeSale(SaleLikeRequestDto request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_USER)
        );

        Sale sale = saleRepository.findById(request.getSaleId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_SALE)
        );

        log.info("user정보: " + user.getUserId() + ", sale정보: " + sale.getSaleId());

        try {
            SaleLikeId saleLikeId = SaleLikeId.builder()
                    .saleId(sale.getSaleId())
                    .userId(user.getUserId())
                    .build();

            SaleLike saleLike = SaleLike.builder()
                    .saleLikeId(saleLikeId)
                    .sale(sale)
                    .user(user)
                    .build();
//            saleLike.setSale(sale);
            log.info("saleLike 인스턴스 생성 후 " + saleLike.getCreatedAt());

            saleLikeRepository.save(saleLike);
            return SaleLikeResponseDto.of(saleLike, true);

        } catch (NullPointerException e) {
            throw new CustomException(ErrorStatus.FAILED_TO_CREATE_SALE_LIKE);
        } catch (Exception e) {
            throw new CustomException(ErrorStatus.FAILED_TO_CREATE_SALE_LIKE);
        }


    }

    // 상품 좋아요 취소
    public SaleLikeResponseDto dislikeSale(SaleLikeRequestDto request) {

        SaleLike saleLike = saleLikeRepository.findBySaleLikeId(SaleLikeId.builder()
                .saleId(request.getSaleId())
                .userId(request.getUserId())
                .build()).orElseThrow(
                () -> new CustomException(ErrorStatus.FAILED_TO_GET_SALE_LIKE)
        );

        Sale sale = saleRepository.findById(request.getSaleId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_SALE)
        );
        saleLike.deleteSale(sale);

        saleLikeRepository.delete(saleLike);
        return SaleLikeResponseDto.of(saleLike, false);
    }

}
