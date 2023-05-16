package sopt.org.CarrotServer.domain.sale;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sale extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer view;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String saleImgUrl;

    @Column(nullable = false)
    private Boolean isUpdated;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Boolean isSuggest;

    @Convert(converter = SaleStatusConverter.class)
    @Column(nullable = false)
    private SaleStatus status;

    @Column(nullable = false)
    private Boolean isDiscount;

    @ManyToOne(fetch = FetchType.LAZY) //다대일(N:1) 관계
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    //JoinColumn은 필드 매핑만 해줌 foreignKey의 ConstraintMode.PROVIDER_DEFAULT는 DB에 따라 제약조건이 안 생길수도 있음
    private User user;
}
