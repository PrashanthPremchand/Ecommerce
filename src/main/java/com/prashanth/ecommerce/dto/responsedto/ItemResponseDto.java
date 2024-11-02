package com.prashanth.ecommerce.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String productName;
    int priceOfOneItem;
    int totalPrice;
    int quantity;
}
