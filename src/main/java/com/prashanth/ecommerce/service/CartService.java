package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.CheckOutRequestDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ItemResponseDto;
import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.exception.*;

import java.util.List;

public interface CartService {
    public CartResponseDto saveToCart(int customerId, Item item);
    public OrderResponseDto checkOutCart(CheckOutRequestDto checkOutRequestDto) throws CustomerDoestNotExistException, InvalidCardException, CartEmptyException, ProductOutOfStockException;
    public CartResponseDto removeItemFromCart(int customerId, int itemId) throws CustomerDoestNotExistException, ItemNotFoundException;
    public List<ItemResponseDto> getAllItemInCart(int customerId) throws CustomerDoestNotExistException, CartEmptyException;
}
