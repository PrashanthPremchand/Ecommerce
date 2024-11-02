package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.ItemRequestDto;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.exception.ProductDoesNotExistException;
import com.prashanth.ecommerce.exception.ProductOutOfStockException;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws CustomerDoestNotExistException, ProductDoesNotExistException, ProductOutOfStockException;
}
