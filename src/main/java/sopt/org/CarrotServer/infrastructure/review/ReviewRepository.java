package sopt.org.CarrotServer.infrastructure.review;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.CarrotServer.domain.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
