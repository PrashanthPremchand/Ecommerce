package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.requestdto.ItemRequestDto;
import com.prashanth.ecommerce.dto.requestdto.OrderRequestDto;
import com.prashanth.ecommerce.dto.responsedto.ItemResponseDto;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.entity.Product;

public class ItemTransformer {
    public static Item  itemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
    public static ItemResponseDto itemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice((item.getRequiredQuantity()) * (item.getProduct().getPrice()))
                .build();
    }
    public static Item orderRequestDtoToItem(OrderRequestDto orderRequestDto, Product product){
        return Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
    }
}
