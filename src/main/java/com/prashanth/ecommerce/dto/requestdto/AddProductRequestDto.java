package com.prashanth.ecommerce.dto.requestdto;

import com.prashanth.ecommerce.enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddProductRequestDto {
    int sellerId;
    String productName;
    int price;
    int quantity;
    ProductCategory productCategory;
}
