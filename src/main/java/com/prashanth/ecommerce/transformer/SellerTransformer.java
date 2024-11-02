package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.entity.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDtoToSeller(AddSellerRequestDto addSellerRequestDto){
        return Seller.builder()
                .name(addSellerRequestDto.getName())
                .emailId(addSellerRequestDto.getEmail())
                .age(addSellerRequestDto.getAge())
                .mobNo(addSellerRequestDto.getMobNo())
                .build();
    }
    public static AddSellerResponseDto sellerToSellerResponseDto(Seller seller){
        return AddSellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();
    }
}
