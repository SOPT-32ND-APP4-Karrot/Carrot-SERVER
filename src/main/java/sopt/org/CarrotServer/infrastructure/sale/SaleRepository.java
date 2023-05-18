package sopt.org.CarrotServer.infrastructure.sale;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.CarrotServer.domain.sale.Sale;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface SaleRepository extends Repository<Sale, Long> {

    // CREATE
    void save(Sale sale);

    // READ
    List<Sale> findAll();

    Optional<Sale> findById(Long id);

    //랜덤 조회 -> JPA는 주로 객체-관계 매핑(ORM)을 위한 기술로 랜덤 조회 기능 내장 x
    //생쿼리는 객체지향 x -> default 메서드로 직접 구현
    default Optional<Sale> findRandomSale() {
        List<Sale> sales = findAll();
        return Optional.ofNullable(sales.get(new Random().nextInt(sales.size())));
    }

    // UPDATE
    // DELETE
}
