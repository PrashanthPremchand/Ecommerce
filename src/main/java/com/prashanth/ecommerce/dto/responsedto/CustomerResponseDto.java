package com.prashanth.ecommerce.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {
    String name;
    String email;
    int age;
    String mobNo;
    String address;
    List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
}
