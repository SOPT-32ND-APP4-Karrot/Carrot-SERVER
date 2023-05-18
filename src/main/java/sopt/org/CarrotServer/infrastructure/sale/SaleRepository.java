package sopt.org.CarrotServer.infrastructure.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.org.CarrotServer.domain.sale.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
