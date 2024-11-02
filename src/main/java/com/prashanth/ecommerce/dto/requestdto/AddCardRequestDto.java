package com.prashanth.ecommerce.dto.requestdto;

import com.prashanth.ecommerce.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddCardRequestDto {
    String mobNo;
    String cardNo;
    int cvv;
    LocalDate expiryDate;
    CardType cardType;
}
