package com.prashanth.ecommerce.dto.responsedto;

import com.prashanth.ecommerce.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String cardNo;
    LocalDate expiryDate;
    CardType cardType;
    String customerName;
}
