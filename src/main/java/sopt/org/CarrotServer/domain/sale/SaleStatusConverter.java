package sopt.org.CarrotServer.domain.sale;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public class SaleStatusConverter implements AttributeConverter<SaleStatus, String> {

    @Override
    public String convertToDatabaseColumn(SaleStatus attribute) {
        return attribute.getStatusCode();
    }

    @Override
    public SaleStatus convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(SaleStatus.class).stream()
                .filter(saleStatus -> saleStatus.getStatusCode().equals(dbData))
                .findAny()
                .orElseThrow(); //TODO: exception 추가 예정
    }
}
