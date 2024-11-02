package com.prashanth.ecommerce.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemRequestDto {
    int customerId;
    int productId;
    int requiredQuantity;
}
