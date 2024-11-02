package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.exception.ProductOutOfStockException;
import com.prashanth.ecommerce.exception.SellerDoesNotExistException;

public interface ProductService {
    public AddProductResponseDto addProduct(AddProductRequestDto addProductRequestDto) throws SellerDoesNotExistException;
    public void decreaseProductQuantity(Item item) throws ProductOutOfStockException;
}
