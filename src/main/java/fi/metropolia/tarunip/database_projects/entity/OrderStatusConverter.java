package fi.metropolia.tarunip.database_projects.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    // Converts enum to text when saving to the database
    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        return status == null ? null : status.name();
    }

    // Converts database text back to enum
    @Override
    public OrderStatus convertToEntityAttribute(String status) {
        return status == null ? null : OrderStatus.valueOf(status);
    }
}



