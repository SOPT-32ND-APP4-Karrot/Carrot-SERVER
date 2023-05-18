package sopt.org.CarrotServer.infrastructure.sale;

import org.springframework.data.repository.Repository;
import sopt.org.CarrotServer.domain.sale.SaleLike;
import sopt.org.CarrotServer.domain.sale.SaleLikeId;

public interface SaleLikeRepository extends Repository<SaleLike, SaleLikeId> {

    // CREATE
    // READ
    Long countBySaleLikeIdSaleId(Long saleId); //SaleLikeId.SaleId

    boolean existsBySaleLikeId(SaleLikeId saleLikeId);
    // UPDATE
    // DELETE
}
