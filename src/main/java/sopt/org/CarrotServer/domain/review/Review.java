package sopt.org.CarrotServer.domain.review;

import lombok.*;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.sale.Sale;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @OneToOne
    @JoinColumn(name = "sender_review_content_id")
    private ReviewContent senderReviewContent;

    @OneToOne
    @JoinColumn(name = "receiver_review_content_id")
    private ReviewContent receiverReviewContent;

    //== 연관관계 메소드 ==//
    // TODO User에 판매자 여부 구분 필드 넣어서 sender 인지 receiver 인지 여기서 set으로 지정
    public void setReview(ReviewContent reviewContent) {
    }

    @Builder
    public Review(Sale sale, ReviewContent senderReviewContent, ReviewContent receiverReviewContent) {
        this.sale = sale;
        this.senderReviewContent = senderReviewContent;
        this.receiverReviewContent = receiverReviewContent;
    }
}
