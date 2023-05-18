package sopt.org.CarrotServer.controller.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleResponseDto;
import sopt.org.CarrotServer.controller.user.dto.response.UserResponseDto;
import sopt.org.CarrotServer.domain.chat.ChatRoom;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoomRepsonseDto {

    private Long chatRoomId;

    private SaleResponseDto sale;

    private UserResponseDto user;

    private String createdAt;

    private String updatedAt;

    public static ChatRoomRepsonseDto of(ChatRoom chatRoom) {
        return new ChatRoomRepsonseDto(
                chatRoom.getChatRoomId(),
                SaleResponseDto.of(chatRoom.getSale()),
                UserResponseDto.of(chatRoom.getWriter()),
                chatRoom.getCreatedAt(),
                chatRoom.getModifiedAt());
    }
}
