package sopt.org.CarrotServer.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.org.CarrotServer.common.dto.ApiResponse;
import sopt.org.CarrotServer.controller.user.dto.request.CreateUserRequestDto;
import sopt.org.CarrotServer.exception.SuccessStatus;
import sopt.org.CarrotServer.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //유저 생성
    @PostMapping("")
    public ApiResponse<Long> createUser(@RequestBody final CreateUserRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_USER_SUCCESS, userService.createUser(request));
    }
}
