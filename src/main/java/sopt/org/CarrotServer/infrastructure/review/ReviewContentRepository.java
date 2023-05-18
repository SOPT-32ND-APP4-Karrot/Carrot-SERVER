package sopt.org.CarrotServer.infrastructure.review;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.CarrotServer.domain.review.ReviewContent;


public interface ReviewContentRepository extends JpaRepository<ReviewContent, Long> {
}
