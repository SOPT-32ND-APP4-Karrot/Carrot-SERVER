package sopt.org.CarrotServer.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserRequestDto {
    private String nickname;
    private String phone;
    private Double temperature;
    private String location;
    private String profileImgUrl;
}
