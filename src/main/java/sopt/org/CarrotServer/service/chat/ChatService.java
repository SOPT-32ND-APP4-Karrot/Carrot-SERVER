package sopt.org.CarrotServer.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.controller.chat.dto.request.CreateChatMessageRequestDto;
import sopt.org.CarrotServer.controller.chat.dto.request.CreateChatRoomRequestDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatMessageResponseDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatResponseDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatRoomRepsonseDto;
import sopt.org.CarrotServer.domain.chat.ChatMessage;
import sopt.org.CarrotServer.domain.chat.ChatRoom;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.infrastructure.chat.ChatMessageRepository;
import sopt.org.CarrotServer.infrastructure.chat.ChatRoomRepository;
import sopt.org.CarrotServer.infrastructure.sale.SaleRepository;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    /**
     * 채팅방 정보 조회하기 (채팅 메시지 리스트 포함)
     * @param chatRoomId
     * @return
     */
    public ChatResponseDto getChatRoomInfo(Long chatRoomId) {

        ChatRoom chatRoom = getChatRoom(chatRoomId);

        List<ChatMessage> chatMessageList = chatRoom.getChatMessageList();
        List<ChatMessageResponseDto> chatMessageListResponse = chatMessageList.stream()
                .map(ChatMessageResponseDto::of)
                .collect(Collectors.toList());

        // TODO ChatRoomId와 ReviewId가 항상 같아도 될까?
        return ChatResponseDto.of(chatRoom, chatMessageListResponse, chatRoom.getSale(), chatRoom.getWriter(), chatRoomId);
    }

    /**
     * 채팅방 ID로 조회하기
     * @param chatRoomId
     * @return
     */
    public ChatRoom getChatRoom(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_CHATROOM)
        );
    }

    /**
     * 채팅 메시지 생성하기
     * @param request
     * @return
     */
    @Transactional
    public ChatMessageResponseDto createChatMessage(CreateChatMessageRequestDto request) {

        User writer = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_USER)
        );

        ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_CHATROOM)
        );

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writer(writer)
                .content(request.getContent())
                .hasKeyword(request.getHasKeyword())
                .build();

        chatMessage.setChatRoom(chatRoom);
        chatMessage.setWriter(writer);

        return ChatMessageResponseDto.of(chatMessage);
    }

    /**
     * 채팅방 생성하기
     * @param request
     * @return
     */
    @Transactional
    public ChatRoomRepsonseDto createChatRoom(CreateChatRoomRequestDto request) {

        User writer = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_USER)
        );

        Sale sale = saleRepository.findById(request.getSaleId()).orElseThrow(
                () -> new CustomException(ErrorStatus.NO_EXISTS_SALE)
        );

        ChatRoom chatRoom = ChatRoom.builder()
                .writer(writer)
                .sale(sale)
                .build();

        chatRoom.setWriter(writer);
        chatRoom.setSale(sale);

        return ChatRoomRepsonseDto.of(chatRoom);
    }
}
