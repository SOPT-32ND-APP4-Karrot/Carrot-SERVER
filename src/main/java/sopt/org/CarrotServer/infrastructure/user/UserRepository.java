package sopt.org.CarrotServer.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.CarrotServer.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
