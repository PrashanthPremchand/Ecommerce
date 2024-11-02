package com.prashanth.ecommerce.dto.responsedto;

import com.prashanth.ecommerce.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddProductResponseDto {
    String productName;
    String sellerName;
    int quantity;
    ProductStatus productStatus;
}
