package sopt.org.CarrotServer.controller.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.controller.user.dto.response.UserResponseDto;
import sopt.org.CarrotServer.domain.chat.ChatMessage;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessageResponseDto {

    private Long chatMessageId;

    private String content;

    private Boolean hasKeyword;

    private String time;

    private UserResponseDto writer;

    public static ChatMessageResponseDto of(ChatMessage chatMessage) {
        return new ChatMessageResponseDto(
                chatMessage.getChatMessageId(),
                chatMessage.getContent(),
                chatMessage.getHasKeyword(),
                chatMessage.getCreatedAt(),
                UserResponseDto.of(chatMessage.getWriter()));
    }
}
