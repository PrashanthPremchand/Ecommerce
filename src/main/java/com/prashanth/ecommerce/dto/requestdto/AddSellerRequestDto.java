package com.prashanth.ecommerce.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddSellerRequestDto {
    String name;
    String email;
    int age;
    String mobNo;
}