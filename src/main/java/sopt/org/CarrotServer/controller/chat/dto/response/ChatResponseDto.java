package sopt.org.CarrotServer.controller.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sopt.org.CarrotServer.controller.sale.dto.response.SaleResponseDto;
import sopt.org.CarrotServer.controller.user.dto.response.UserResponseDto;
import sopt.org.CarrotServer.domain.chat.ChatRoom;
import sopt.org.CarrotServer.domain.sale.Sale;
import sopt.org.CarrotServer.domain.user.User;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatResponseDto {

    private Long chatRoomId;

    private List<ChatMessageResponseDto> chatMessageList;

    private SaleResponseDto sale;

    private UserResponseDto seller;

    private Long reviewId;

    public static ChatResponseDto of(ChatRoom chatRoom, List<ChatMessageResponseDto> chatMessageList, Sale sale, User user, Long reviewId) {
        return new ChatResponseDto(
                chatRoom.getChatRoomId(),
                chatMessageList,
                SaleResponseDto.of(sale),
                UserResponseDto.of(user),
                reviewId
        );
    }

}
