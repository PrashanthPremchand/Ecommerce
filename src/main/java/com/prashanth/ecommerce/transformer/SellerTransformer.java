package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ProductResponseDto;
import com.prashanth.ecommerce.dto.responsedto.SellerResponseDto;
import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.entity.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerTransformer {
    public static Seller sellerRequestDtoToSeller(AddSellerRequestDto addSellerRequestDto){
        return Seller.builder()
                .name(addSellerRequestDto.getName())
                .emailId(addSellerRequestDto.getEmail())
                .age(addSellerRequestDto.getAge())
                .mobNo(addSellerRequestDto.getMobNo())
                .build();
    }

    public static AddSellerResponseDto sellerToAddSellerResponseDto(Seller seller){
        return AddSellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product currProduct : seller.getProducts()){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(currProduct));
        }
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .emailId(seller.getEmailId())
                .mobNo(seller.getMobNo())
                .productResponseDtoList(productResponseDtoList)
                .build();
    }
}
