package sopt.org.CarrotServer.controller.chat.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateChatRoomRequestDto {

    private Long saleId;

    private Long userId;

}
