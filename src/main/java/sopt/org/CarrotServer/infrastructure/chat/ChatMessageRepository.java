package sopt.org.CarrotServer.infrastructure.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.CarrotServer.domain.chat.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
