package sopt.org.CarrotServer.controller.chat.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class ChatRoomRequestDto {

    private Long chatRoomId;
}
