package sopt.org.CarrotServer.domain.sale;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable //엔티티클래스에서 사용할 수 있는 임베디드 클래스
//엔티티 클래스에서 해당 객체를 가지고 있는 경우, 해당 객체의 속성을 엔티티 테이블에서 컬럼으로 매핑
public class SaleLikeId implements Serializable {
    //객체를 바이트형태로 직렬화
    //JPA에서 엔티티를 데이터베이스에 저장 또는 조회 시 객체를 직렬화하여 수행
    //복합키를 구현한 클래스도 직렬화 가능해야 함
    @Column
    private Long userId;
    @Column
    private Long saleId;
}
