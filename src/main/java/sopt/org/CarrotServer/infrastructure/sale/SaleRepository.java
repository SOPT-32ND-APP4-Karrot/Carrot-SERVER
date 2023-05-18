package sopt.org.CarrotServer.infrastructure.sale;

import org.springframework.data.repository.Repository;
import sopt.org.CarrotServer.domain.sale.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends Repository<Sale, Long> {

    // CREATE
    void save(Sale sale);

    // READ
    List<Sale> findAll();
    Optional<Sale> findById(Long id);

    // UPDATE
    // DELETE
}
