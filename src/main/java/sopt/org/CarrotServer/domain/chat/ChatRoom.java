package sopt.org.CarrotServer.domain.chat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CHAT_ROOM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Id
    @Column(name = "chat_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User writer;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @Builder
    public ChatRoom(Sale sale, User writer, List<ChatMessage> chatMessageList) {
        this.sale = sale;
        this.writer = writer;
        this.chatMessageList = chatMessageList;
    }

    //== 연관관계 메서드 ==//
    public void setWriter(User user) {
        if (this.writer != null) {
            this.writer.getChatRoomList().remove(this);
        }

        this.writer = user;
        writer.getChatRoomList().add(this);
    }
}
