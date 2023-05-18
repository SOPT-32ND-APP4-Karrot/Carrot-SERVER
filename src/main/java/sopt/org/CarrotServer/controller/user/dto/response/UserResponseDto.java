package sopt.org.CarrotServer.controller.user.dto.response;

import sopt.org.CarrotServer.domain.user.User;

public class UserResponseDto {

    public static UserResponseDto of(User user) {
        return new UserResponseDto();
    }
}
