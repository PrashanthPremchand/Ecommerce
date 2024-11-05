package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.OrderRequestDto;
import com.prashanth.ecommerce.dto.responsedto.CustomerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.entity.Card;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.entity.Ordered;
import com.prashanth.ecommerce.exception.*;

import java.util.List;

public interface OrderedService {
    public Ordered placedOrder(Customer customer, Card card) throws ProductOutOfStockException, ProductDoesNotExistException;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerDoestNotExistException, ProductDoesNotExistException, InvalidCardException, ProductOutOfStockException;
    public List<OrderResponseDto> getAllOrdersByCustomer(int customerId) throws CustomerDoestNotExistException;
    public List<OrderResponseDto> getFiveOrders(int customerId) throws CustomerDoestNotExistException, OrderDoesNotExist;
    public String deleteOrder(int orderId) throws OrderDoesNotExist;
    public String getCustomerNameWithMaxTotalValue() throws OrderDoesNotExist;
}
