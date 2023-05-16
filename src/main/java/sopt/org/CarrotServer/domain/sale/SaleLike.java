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
public class SaleLike extends BaseTimeEntity {

    @EmbeddedId
    private SaleLikeId id; //복합키

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") //SaleLikeId 클래스의 userId와 매핑
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("saleId") //SaleLikeId 클래스의 saleId와 매핑
    @JoinColumn(name = "saleId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Sale sale;
}
