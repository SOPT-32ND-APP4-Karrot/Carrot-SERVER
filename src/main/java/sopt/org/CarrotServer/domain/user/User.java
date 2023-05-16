package sopt.org.CarrotServer.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.sale.Sale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String profileImgUrl;

    //판매한 상품
    @OneToMany(mappedBy = "user") //일대다(1:N)
    private final List<Sale> sales = new ArrayList<>();
}
