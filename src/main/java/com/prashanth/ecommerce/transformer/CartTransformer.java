package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.entity.Cart;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CartTransformer {

    public static Cart itemToCart(Customer customer, Item item){
        Cart cart = customer.getCart();
        int newTotal = cart.getCartTotal() + (item.getRequiredQuantity() * item.getProduct().getPrice());
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);
        return cart;
    }

    public static CartResponseDto cartToCartResponseDto(Cart cart, Customer customer){
        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .numberOfItems(cart.getNumberOfItems())
                .customerName(customer.getName())
                .build();
    }
}
