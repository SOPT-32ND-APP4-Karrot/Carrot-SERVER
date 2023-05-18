package sopt.org.CarrotServer.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.chat.ChatMessage;
import sopt.org.CarrotServer.domain.chat.ChatRoom;
import sopt.org.CarrotServer.domain.review.ReviewContent;
import sopt.org.CarrotServer.domain.sale.Sale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
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

    // TODO User 에서도 양방향 연관관계 매핑을 해줘야 할까?
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewContent> reviewContentList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    @Builder
    public User(String nickname, String phone, Double temperature, String location, String profileImgUrl) {
        this.nickname = nickname;
        this.phone = phone;
        this.temperature = temperature;
        this.location = location;
        this.profileImgUrl = profileImgUrl;
    }

    //판매한 상품
    @OneToMany(mappedBy = "user") //일대다(1:N)
    private final List<Sale> sales = new ArrayList<>();
}
