package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.entity.Ordered;
import org.hibernate.query.Order;

public class OrderTransformer {
    public static OrderResponseDto orderedToOrderResponseDto(Ordered ordered){
        return OrderResponseDto.builder()
                .orderNo(ordered.getOrderNo())
                .totalValue(ordered.getTotalValue())
                .orderDate(ordered.getOrderDate())
                .cardUsed(ordered.getCardUsed())
                .customerName(ordered.getCustomer().getName())
                .build();
    }
}
