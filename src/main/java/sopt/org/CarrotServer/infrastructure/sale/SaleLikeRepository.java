package sopt.org.CarrotServer.infrastructure.sale;

import org.springframework.data.repository.Repository;
import sopt.org.CarrotServer.domain.sale.SaleLike;
import sopt.org.CarrotServer.domain.sale.SaleLikeId;

import java.util.Optional;

public interface SaleLikeRepository extends Repository<SaleLike, SaleLikeId> {

    // CREATE
    void save(SaleLike saleLike);

    // READ
    Long countBySaleLikeIdSaleId(Long saleId); //SaleLikeId.SaleId

    boolean existsBySaleLikeId(SaleLikeId saleLikeId);

    Optional<SaleLike> findBySaleLikeId(SaleLikeId saleLikeId);

    // UPDATE
    // DELETE
    void delete(SaleLike saleLike);
}
