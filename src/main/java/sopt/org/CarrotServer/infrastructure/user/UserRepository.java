package sopt.org.CarrotServer.infrastructure.user;


import org.springframework.data.repository.Repository;
import sopt.org.CarrotServer.domain.user.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    // CREATE
    void save(User user);
    // READ
    Optional<User> findById(Long id);
    // UPDATE
    // DELETE
}
