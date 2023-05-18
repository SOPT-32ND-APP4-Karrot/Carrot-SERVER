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


    @Builder
    public Review(Sale sale, ReviewContent senderReviewContent, ReviewContent receiverReviewContent) {
        this.sale = sale;
        this.senderReviewContent = senderReviewContent;
        this.receiverReviewContent = receiverReviewContent;
    }

    //== 연관관계 메소드 ==//
    public void setSenderReviewContent(ReviewContent reviewContent) {
        this.senderReviewContent = reviewContent;
    }
    public void setReceiverReviewContent(ReviewContent reviewContent) {
        this.receiverReviewContent = reviewContent;
    }
}
