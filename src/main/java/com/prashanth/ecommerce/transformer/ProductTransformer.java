package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ProductResponseDto;
import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.enums.ProductStatus;

public class ProductTransformer {
    public static Product addProductRequestDtoToProduct(AddProductRequestDto addProductRequestDto){
        return Product.builder()
                .name(addProductRequestDto.getProductName())
                .price(addProductRequestDto.getPrice())
                .quantity(addProductRequestDto.getQuantity())
                .productCategory(addProductRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static AddProductResponseDto productToAddProductResponseDto(Product product){
        return AddProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
