package sopt.org.CarrotServer.domain.review;

import lombok.*;
import sopt.org.CarrotServer.domain.BaseTimeEntity;

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

    @Enumerated()
    @Column(name = "content")
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    // private User writer;

    @Builder
    public ReviewContent(String content) {
        this.content = content;
    }
}
