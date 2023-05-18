package sopt.org.CarrotServer.controller.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.CarrotServer.domain.user.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailResponseDto {
    private Long userId;
    private String profileImgUrl;
    private String nickname;
    private String location;
    private Double temperature;

    public static UserDetailResponseDto of(User user) {
        return new UserDetailResponseDto(
                user.getUserId(),
                user.getProfileImgUrl(),
                user.getNickname(),
                user.getLocation(),
                user.getTemperature()
        );
    }
}
