package sopt.org.CarrotServer.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity 를 상속하는 경우, 필드(createdAt, modifiedAt)들도 모두 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  // BastTimeEntity 클래스에 시간 데이터를 자동으로 매핑하여 값을 넣어주는 JPA Auditing 기능 포함
public abstract class BaseTimeEntity {

    @Column(name = "created_at")
    @CreatedDate   // Entity 가 생성되어 저장될 때 시간이 자동 저장된다.
    private String createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate  // 조회한 Entity 의 값을 변경할 때 최종 수정 시간이 자동 저장된다.
    private String updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));

    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

}
