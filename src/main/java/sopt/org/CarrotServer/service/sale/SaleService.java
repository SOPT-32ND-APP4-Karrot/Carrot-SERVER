package sopt.org.CarrotServer.service.sale;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sopt.org.CarrotServer.controller.sale.dto.request.CreateSaleRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.request.SaleLikeRequestDto;
import sopt.org.CarrotServer.controller.sale.dto.response.*;
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
import sopt.org.CarrotServer.service.user.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final SaleLikeRepository saleLikeRepository;
    private final SaleRepository saleRepository;
    private final UserService userService;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    //상품 생성
    @Transactional
    public Long createSale(final CreateSaleRequestDto request) {
        User user = userRepository.findById(Long.valueOf(request.getUserId())).orElseThrow();

        String saleImgUrl;
        try{
            MultipartFile multipartFile = request.getSaleImgUrl();
            //랜덤 파일명 생성
            String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
            //파일 사이즈 설정
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(multipartFile.getInputStream().available());
            //s3에 파일 업로드
            amazonS3Client.putObject(bucket, fileName, multipartFile.getInputStream(), objMeta);

            saleImgUrl = amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sale sale = Sale.builder()
                .category(request.getCategory())
                .view(request.getView())
                .title(request.getTitle())
                .description(request.getDescription())
                .saleImgUrl(saleImgUrl)
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
            User user = userService.getUser(sale.getUser().getUserId());
            int likeCount = Math.toIntExact(saleLikeRepository.countBySaleLikeIdSaleId(sale.getSaleId()));

            //임시로 좋아요는 판매자가 눌렀는지 여부로 구현
            boolean isCheckLike = saleLikeRepository.existsBySaleLikeId(
                    SaleLikeId.builder().saleId(sale.getSaleId()).userId(user.getUserId()).build());

            saleList.add(SaleResponseDto.of(
                    sale.getSaleId(),
                    sale.getTitle(),
                    sale.getSaleImgUrl(),
                    user.getLocation(),
                    sale.getIsUpdated() ? sale.getUpdatedAt() : sale.getCreatedAt(),
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
        User user = userService.getUser(sale.getUser().getUserId());

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

;        return SaleDetailResponseDto.of(sale, likeCount, isCheckLike, user, chatRoomId);
    }

    //[GET] 상세_광고 조회
    public SaleSimpleResponseDto getRandomSale() {
        Sale sale = saleRepository.findRandomSale().orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_SALE, ErrorStatus.NO_EXISTS_SALE.getMessage())
        );

        return SaleSimpleResponseDto.of(sale);
    }

    //[GET] 상세_판매 상품 조회
    public SellerSaleResponseDto getSaleByUserId(final Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_USER, ErrorStatus.NO_EXISTS_USER.getMessage())
        );

        return SellerSaleResponseDto.of(user);
    }

    //[GET] 상세_함께 본 상품 조회
    public List<SaleInfoDto> getSaleByCategory(final Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_SALE, ErrorStatus.NO_EXISTS_SALE.getMessage())
        );

        String category = sale.getCategory();

        return saleRepository.findTop6ByCategoryAndSaleIdNot(category, saleId).stream().map(SaleInfoDto::of).collect(Collectors.toList());
    }

    // 상품 좋아요 체크
    @Transactional
    public SaleLikeResponseDto likeSale(SaleLikeRequestDto request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_USER)
        );

        Sale sale = saleRepository.findById(request.getSaleId()).orElseThrow(
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_SALE)
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
            log.info("saleLike 인스턴스 생성 후 " + saleLike.getCreatedAt());

            saleLikeRepository.save(saleLike);
            SaleLikeResponseDto response = SaleLikeResponseDto.of(saleLike, true);
            response.setLikeCount(Math.toIntExact(saleLikeRepository.countBySaleLikeIdSaleId(sale.getSaleId())));

            return response;

        } catch (NullPointerException e) {
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
                () -> new NotFoundException(ErrorStatus.NO_EXISTS_SALE)
        );

        saleLikeRepository.delete(saleLike);
        SaleLikeResponseDto response =  SaleLikeResponseDto.of(saleLike, false);
        response.setLikeCount(Math.toIntExact(saleLikeRepository.countBySaleLikeIdSaleId(sale.getSaleId())));

        return response;
    }



}
