package com.prashanth.ecommerce.dto.responsedto;

import com.prashanth.ecommerce.enums.ProductCategory;
import com.prashanth.ecommerce.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String name;
    int price;
    int quantity;
    ProductCategory productCategory;
    ProductStatus productStatus;
}
