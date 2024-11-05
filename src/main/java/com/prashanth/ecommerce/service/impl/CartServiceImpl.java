package com.prashanth.ecommerce.service.impl;
import com.prashanth.ecommerce.dto.requestdto.CheckOutRequestDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;

import com.prashanth.ecommerce.dto.responsedto.ItemResponseDto;
import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.entity.*;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.repository.*;
import com.prashanth.ecommerce.service.CartService;
import com.prashanth.ecommerce.service.OrderedService;
import com.prashanth.ecommerce.transformer.CartTransformer;
import com.prashanth.ecommerce.transformer.ItemTransformer;
import com.prashanth.ecommerce.transformer.OrderTransformer;
import com.prashanth.ecommerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    Validation validation;
    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public CartResponseDto saveToCart(int customerId, Item item) {
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = CartTransformer.itemToCart(customer, item);
        Cart savedCart = cartRepository.save(cart);
        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponseDto(savedCart, customer);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item currItem : cart.getItems()){
            ItemResponseDto currItemResponseDto = ItemTransformer.itemToItemResponseDto(currItem);
            itemResponseDtoList.add(currItemResponseDto);
        }
        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutRequestDto checkOutRequestDto) throws CustomerDoestNotExistException, InvalidCardException, CartEmptyException, ProductOutOfStockException, ProductDoesNotExistException {
        validation.customerValidation(checkOutRequestDto.getCustomerId());
        Customer customer = customerRepository.findById(checkOutRequestDto.getCustomerId()).get();

        Card card = cardRepository.findByCardNo(checkOutRequestDto.getCardNo());
        if(card == null || card.getCvv() != checkOutRequestDto.getCvv() || customer != card.getCustomer()){
            throw new InvalidCardException("Invalid Card");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0){
            throw new CartEmptyException("Your cart is Empty");
        }

        try{
            Ordered ordered = orderedService.placedOrder(customer, card);
            customer.getOrderedList().add(ordered);
            resetCart(cart);
            Ordered savedOrder = orderedRepository.save(ordered);
            OrderResponseDto orderResponseDto = OrderTransformer.orderedToOrderResponseDto(savedOrder);
            List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
            for(Item currItem : savedOrder.getItems()){
                itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(currItem));
            }
            orderResponseDto.setItems(itemResponseDtoList);
            return orderResponseDto;
        }
        catch (ProductOutOfStockException e){
            throw new ProductOutOfStockException(e.getMessage());
        } catch (ProductDoesNotExistException e) {
            throw new ProductDoesNotExistException(e.getMessage());
        }

    }

    @Override
    public CartResponseDto removeItemFromCart(int customerId, int itemId) throws CustomerDoestNotExistException, ItemNotFoundException {
        validation.customerValidation(customerId);
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        validation.itemValidation(itemId);
        Item item = itemRepository.findById(itemId).get();

        boolean flag = false;
        for(Item currItem : cart.getItems()){
            if(currItem == item) {
                flag = true;
                break;
            }
        }
        if(!flag) throw new ItemNotFoundException("Item dose not exist in this cart");
        int newTotal = cart.getCartTotal() - (item.getRequiredQuantity() * item.getProduct().getPrice());
        cart.setCartTotal(newTotal);
        cart.getItems().remove(item);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(null);
        customer.setCart(cart);
        customerRepository.save(customer);

        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponseDto(cart, customer);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item currItem : cart.getItems()){
            ItemResponseDto currItemResponseDto = ItemTransformer.itemToItemResponseDto(currItem);
            itemResponseDtoList.add(currItemResponseDto);
        }
        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public List<ItemResponseDto> getAllItemInCart(int customerId) throws CustomerDoestNotExistException, CartEmptyException {
        validation.customerValidation(customerId);
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();
        if(cart == null) throw new CartEmptyException("Cart is empty");
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item currItem : cart.getItems()){
            ItemResponseDto currItemResponseDto = ItemTransformer.itemToItemResponseDto(currItem);
            itemResponseDtoList.add(currItemResponseDto);
        }
        return itemResponseDtoList;
    }

    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        cart.setNumberOfItems(0);
        for(Item currItem : cart.getItems()){
            currItem.setCart(null);
        }
        cart.getItems().clear();
    }
}