package sopt.org.CarrotServer.domain.review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name = "content")
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    // private User writer;
}
