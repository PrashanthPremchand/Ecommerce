package com.prashanth.ecommerce.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String orderNo;
    int totalValue;
    Date orderDate;
    String cardUsed;
    List<ItemResponseDto> items;
    String customerName;
}
