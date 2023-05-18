package sopt.org.CarrotServer.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatMessageResponseDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatResponseDto;
import sopt.org.CarrotServer.domain.chat.ChatMessage;
import sopt.org.CarrotServer.domain.chat.ChatRoom;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.infrastructure.chat.ChatRoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;


    public ChatResponseDto getChatRoomInfo(Long chatRoomId) {

        ChatRoom chatRoom = getChatRoom(chatRoomId);

        List<ChatMessage> chatMessageList = chatRoom.getChatMessageList();
        List<ChatMessageResponseDto> chatMessageListResponse = chatMessageList.stream()
                .map(ChatMessageResponseDto::of)
                .collect(Collectors.toList());

        // TODO ChatRoomId와 ReviewId가 항상 같아도 될까?
        return ChatResponseDto.of(chatRoom, chatMessageListResponse, chatRoom.getSale(), chatRoom.getWriter(), chatRoomId);
    }

    public ChatRoom getChatRoom(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_CHATROOM)
        );
    }
}
