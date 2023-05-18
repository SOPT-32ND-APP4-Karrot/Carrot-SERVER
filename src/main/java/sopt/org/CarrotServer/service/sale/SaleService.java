package sopt.org.CarrotServer.service.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.controller.sale.dto.request.CreateSaleRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleResponseDto;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.sale.SaleLikeId;
import sopt.org.CarrotServer.domain.sale.SaleStatus;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.NotFoundException;
import sopt.org.CarrotServer.infrastructure.sale.SaleLikeRepository;
import sopt.org.CarrotServer.infrastructure.sale.SaleRepository;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
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

//    public List<PostResponseDto> getPostByUserId(final Long userId) {
//        final List<PostResponseDto> postList = new ArrayList<>();
//        postRepository.findAllByUserId(userId).forEach((p) ->
//                postList.add(PostResponseDto.of(p.getId(), p.getTitle(), p.getContent()))
//        );
//        return postList;
//    }
}
