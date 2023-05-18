package sopt.org.CarrotServer.domain.chat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.CarrotServer.domain.BaseTimeEntity;
import sopt.org.CarrotServer.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "CHAT_MESSAGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseTimeEntity {

    @Id
    @Column(name = "chat_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "has_keyword", nullable = false)
    private boolean hasKeyword;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User writer;


    //== 연관관계 메소드 ==//
    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.getChatMessageList().add(this);
    }

    public void setWriter(User user) {
        if (this.writer != null) {
            this.writer.getChatMessageList().remove(this);
        }

        this.writer = user;
        writer.getChatMessageList().add(this);
    }
}
