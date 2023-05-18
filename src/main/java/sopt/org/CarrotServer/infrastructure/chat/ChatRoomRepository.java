package sopt.org.CarrotServer.infrastructure.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.CarrotServer.domain.chat.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
