package com.prashanth.ecommerce.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckOutRequestDto {
    int customerId;
    String cardNo;
    int cvv;
}
