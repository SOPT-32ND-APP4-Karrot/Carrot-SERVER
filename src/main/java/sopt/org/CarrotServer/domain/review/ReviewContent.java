package sopt.org.CarrotServer.domain.review;

import lombok.*;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW_CONTENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewContent extends BaseTimeEntity {

    @Id
    @Column(name = "review_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewContentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "content", nullable = false)
    private ReviewCategory content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User writer;

    @Builder
    public ReviewContent(ReviewCategory content, User writer) {
        this.content = content;
        this.writer = writer;
    }

    //== 연관관계 메소드 ==//
    public void setWriter(User user) {
        if (this.writer != null) {
            this.writer.getReviewContentList().remove(this);
        }

        this.writer = user;
        writer.getReviewContentList().add(this);
    }
}
