package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.OrderRequestDto;
import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderedController {
    @Autowired
    OrderedService orderedService;

    //placing an order
    @PostMapping("/placeOrder")
    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws ProductOutOfStockException, ProductDoesNotExistException, CustomerDoestNotExistException, InvalidCardException {
        return orderedService.placeOrder(orderRequestDto);
    }

    //get all the orders for a customer
    @GetMapping("/get_all_order_by_customer")
    public List<OrderResponseDto> getAllOrdersByCustomer(@RequestParam("customerId")int customerId) throws CustomerDoestNotExistException {
        return orderedService.getAllOrdersByCustomer(customerId);
    }

    // get recent 5 orders by a customer
    @GetMapping("/get_recent_5_orders")
    public List<OrderResponseDto> getFiveOrders(@RequestParam("customerId")int customerId) throws CustomerDoestNotExistException, OrderDoesNotExist {
        return orderedService.getFiveOrders(customerId);
    }
}
