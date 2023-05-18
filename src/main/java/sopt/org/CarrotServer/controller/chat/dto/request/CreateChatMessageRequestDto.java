package sopt.org.CarrotServer.controller.chat.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateChatMessageRequestDto {

    private Long userId;

    private String content;

    private Long chatRoomId;

    private Boolean hasKeyword;
}
