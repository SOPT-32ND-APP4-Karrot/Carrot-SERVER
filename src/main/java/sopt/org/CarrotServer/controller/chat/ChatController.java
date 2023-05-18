package sopt.org.CarrotServer.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatResponseDto;
import sopt.org.CarrotServer.exception.SuccessStatus;
import sopt.org.CarrotServer.exception.model.CustomException;
import sopt.org.CarrotServer.service.chat.ChatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{chatRoomId}")
    public ApiResponse<ChatResponseDto> getChatRoom(@PathVariable("chatRoomId") final Long chatRoomId) {
        try {
            return ApiResponse.success(SuccessStatus.GET_CHATROOM_INFO_SUCCESS, chatService.getChatRoomInfo(chatRoomId));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }
}
