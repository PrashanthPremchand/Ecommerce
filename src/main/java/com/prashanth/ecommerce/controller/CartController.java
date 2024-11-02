package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.CheckOutRequestDto;
import com.prashanth.ecommerce.dto.requestdto.ItemRequestDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ItemResponseDto;
import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.service.CartService;
import com.prashanth.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ItemService itemService;

    //Add to cart
    @PostMapping("/add")
    public CartResponseDto addToCart(@RequestBody ItemRequestDto itemRequestDto) throws ProductOutOfStockException, ProductDoesNotExistException, CustomerDoestNotExistException {
        Item item =  itemService.addItem(itemRequestDto);
        return cartService.saveToCart(itemRequestDto.getCustomerId(), item);
    }

    //Checkout from cart
    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckOutRequestDto checkOutRequestDto) throws ProductOutOfStockException, CustomerDoestNotExistException, CartEmptyException, InvalidCardException {
        return cartService.checkOutCart(checkOutRequestDto);
    }

    //Remove from cart
    @PutMapping("/remove_item_from_cart")
    public CartResponseDto removeItemFromCart(@RequestParam("customerId")int customerId, @RequestParam("itemId")int itemId) throws CustomerDoestNotExistException, ItemNotFoundException {
        return cartService.removeItemFromCart(customerId, itemId);
    }

    //View all item in cart
    @GetMapping("/view_all_items")
    public List<ItemResponseDto> getAllItemsInCart(@RequestParam("customerId")int customerId) throws CustomerDoestNotExistException, CartEmptyException {
        return cartService.getAllItemInCart(customerId);
    }
}
