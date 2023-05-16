package sopt.org.CarrotServer.domain.review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.CarrotServer.domain.BaseTimeEntity;

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

    //    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "sale_id")
    // private Sale sale;

    @OneToOne
    @JoinColumn(name = "sender_review_content_id")
    private ReviewContent senderReviewContent;

    @OneToOne
    @JoinColumn(name = "receiver_review_content_id")
    private ReviewContent receiverReviewContent;


}
