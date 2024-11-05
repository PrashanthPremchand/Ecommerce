package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ProductResponseDto;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.exception.*;

import java.util.List;

public interface ProductService {
    public AddProductResponseDto addProduct(AddProductRequestDto addProductRequestDto) throws SellerDoesNotExistException;
    public void decreaseProductQuantity(Item item) throws ProductOutOfStockException, ProductDoesNotExistException;
    public List<ProductResponseDto> getAllProductBySellerEmail(String email) throws SellerDoesNotExistException;
    public String deleteProduct(int sellerId, int productId) throws ProductDoesNotExistException, SellerDoesNotExistException, IncorrectSellerException, CustomerDoestNotExistException, ItemNotFoundException;
}
