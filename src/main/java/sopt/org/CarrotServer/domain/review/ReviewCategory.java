package sopt.org.CarrotServer.domain.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReviewCategory {

    /**
     * 좋아요!, 최고예요!
     */
    DESCRIPTION("상품상태가 설명한 것과 같아요."),
    CHEAP("좋은 상품을 저렴하게 판매해요."),
    DETAIL("상품 설명이 자세해요."),
    SHARE("나눔을 해주셨어요."),
    KIND("친절하고 매너가 좋아요."),
    TIME("시간 약속을 잘 지켜요."),
    FAST_RESPOND("응답이 빨라요."),

    /**
     * 별로예요!
     */
    TIME_BAD("시간약속을 안 지켜요."),
    CHAT_NOT_RESPOND("채팅 메시지를 읽고도 답이 없어요."),
    UNWANTED_REQUIRE_PRICE("원하지 않는 가격을 계속 요구해요."),
    NOT_CLEARLY_TIME("예약만 하고 거래 시간을 명확하게 알려주지 않아요."),
    NOT_CONTACT("거래 시간과 장소를 정한 후 연락이 안돼요."),
    NOT_COME_PLACE("약속 장소에 나타나지 않았어요."),
    DIRECTLY_CANCEL("거래 시간과 장소를 정한 후 거래 직전 취소했어요."),
    ETC("직접 입력")  //TODO 후기 카테고리 외 직접입력 구현 고민


    ;

    private final String name;

    public static ReviewCategory nameOf(String name) {
        for (ReviewCategory category : ReviewCategory.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return ETC;
    }

}
