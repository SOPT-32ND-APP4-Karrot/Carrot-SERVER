package sopt.org.CarrotServer.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.CarrotServer.controller.user.dto.request.CreateUserRequestDto;
import sopt.org.CarrotServer.domain.user.User;
import sopt.org.CarrotServer.exception.ErrorStatus;
import sopt.org.CarrotServer.exception.model.NotFoundException;
import sopt.org.CarrotServer.infrastructure.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //유저 생성
    public Long createUser(CreateUserRequestDto request) {
        User user = User.builder()
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .temperature(request.getTemperature())
                .location(request.getLocation())
                .profileImgUrl(request.getProfileImgUrl())
                .build();

        userRepository.save(user);
        return user.getUserId();
    }

    // 유저 조회
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NO_EXISTS_USER, ErrorStatus.NO_EXISTS_USER.getMessage()));

    }
}
