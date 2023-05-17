package sopt.org.CarrotServer.domain.sale;

import lombok.Getter;

@Getter
public enum SaleStatus {
    RESERVED("예약중", "1"),
    ON_SALE("판매중", "2"),
    COMPLETED("거래완료", "3");

    private final String status;
    private final String statusCode;

    SaleStatus(String status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }
}