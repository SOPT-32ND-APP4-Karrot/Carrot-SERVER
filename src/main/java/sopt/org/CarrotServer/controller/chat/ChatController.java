package sopt.org.CarrotServer.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.chat.dto.request.CreateChatMessageRequestDto;
import sopt.org.CarrotServer.controller.chat.dto.request.CreateChatRoomRequestDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatMessageResponseDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatResponseDto;
import sopt.org.CarrotServer.controller.chat.dto.response.ChatRoomRepsonseDto;
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

    @PostMapping("/message")
    public ApiResponse<ChatMessageResponseDto> createChatMessage(@RequestBody final CreateChatMessageRequestDto request) {
        try {
            return ApiResponse.success(SuccessStatus.CREATE_CHAT_MESSAGE_SUCCESS, chatService.createChatMessage(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }

    @PostMapping("/room")
    public ApiResponse<ChatRoomRepsonseDto> createChatRoom(@RequestBody final CreateChatRoomRequestDto request) {
        try {
            return ApiResponse.success(SuccessStatus.CREATE_CHAT_ROOM_SUCCESS, chatService.createChatRoom(request));
        } catch (CustomException e) {
            return ApiResponse.error(e.getErrorStatus());
        }
    }
}
