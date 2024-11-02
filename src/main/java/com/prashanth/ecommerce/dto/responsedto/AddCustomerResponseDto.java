package com.prashanth.ecommerce.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddCustomerResponseDto {
    String name;
    String message;
}
