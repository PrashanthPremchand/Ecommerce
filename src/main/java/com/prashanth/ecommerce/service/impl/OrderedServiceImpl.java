package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.OrderRequestDto;
import com.prashanth.ecommerce.dto.responsedto.ItemResponseDto;
import com.prashanth.ecommerce.dto.responsedto.OrderResponseDto;
import com.prashanth.ecommerce.entity.*;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.repository.CardRepository;
import com.prashanth.ecommerce.repository.CustomerRepository;
import com.prashanth.ecommerce.repository.OrderedRepository;
import com.prashanth.ecommerce.repository.ProductRepository;
import com.prashanth.ecommerce.service.OrderedService;
import com.prashanth.ecommerce.service.ProductService;
import com.prashanth.ecommerce.transformer.ItemTransformer;
import com.prashanth.ecommerce.transformer.OrderTransformer;
import com.prashanth.ecommerce.validation.Validation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderedServiceImpl implements OrderedService {
    @Autowired
    ProductService productService;
    @Autowired
    Validation validation;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderedRepository orderedRepository;

    @Override
    public Ordered placedOrder(Customer customer, Card card) throws ProductOutOfStockException {
        Cart cart = customer.getCart();
        Ordered ordered = new Ordered();
        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        ordered.setCardUsed(maskedCardNo);
        ordered.setCustomer(customer);

        for(Item currItem : cart.getItems()){
            try{
                productService.decreaseProductQuantity(currItem);
                ordered.getItems().add(currItem);
            }
            catch (ProductOutOfStockException e){
                throw new ProductOutOfStockException(e.getMessage());
            }
        }
        for(Item currItem : cart.getItems()){
            currItem.setOrdered(ordered);
        }

        ordered.setTotalValue(cart.getCartTotal());

        return ordered;
    }

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerDoestNotExistException, ProductDoesNotExistException, InvalidCardException, ProductOutOfStockException {
        validation.customerValidation(orderRequestDto.getCustomerId());
        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();

        validation.productValidation(orderRequestDto.getProductId());
        Product product = productRepository.findById(orderRequestDto.getProductId()).get();

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card == null || card.getCustomer() != customer || card.getCvv() != orderRequestDto.getCvv()){
            throw new InvalidCardException("Your card is invalid!");
        }

        Item item = ItemTransformer.orderRequestDtoToItem(orderRequestDto, product);
        try{
            productService.decreaseProductQuantity(item);
        } catch (ProductOutOfStockException e){
            throw new ProductOutOfStockException(e.getMessage());
        }

        Ordered ordered = new Ordered();
        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));
        ordered.setCardUsed(generateMaskedCard(card.getCardNo()));
        ordered.setCustomer(customer);
        item.setOrdered(ordered);
        ordered.getItems().add(item);
        ordered.setTotalValue(item.getRequiredQuantity() * product.getPrice());

        customer.getOrderedList().add(ordered);
        product.getItemList().add(item);

        Ordered savedOrdered = orderedRepository.save(ordered);

        OrderResponseDto orderResponseDto = OrderTransformer.orderedToOrderResponseDto(savedOrdered);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(item));
        orderResponseDto.setItems(itemResponseDtoList);

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByCustomer(int customerId) throws CustomerDoestNotExistException {
        validation.customerValidation(customerId);
        Customer customer = customerRepository.findById(customerId).get();

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for( Ordered currOrdered : customer.getOrderedList()){
            orderResponseDtoList.add(OrderTransformer.orderedToOrderResponseDto(currOrdered));
        }

        return orderResponseDtoList;
    }

    @Override
    public List<OrderResponseDto> getFiveOrders(int customerId) throws CustomerDoestNotExistException, OrderDoesNotExist {
        List<Ordered> orderedList = orderedRepository.findByCustomer_CustomerId(customerId);
        if(orderedList == null) throw new OrderDoesNotExist("NO order for this customer ID");

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for( Ordered currOrdered : orderedList){
            orderResponseDtoList.add(OrderTransformer.orderedToOrderResponseDto(currOrdered));
        }

        return orderResponseDtoList;
    }

    public String generateMaskedCard(String cardNo){
        int length = cardNo.length();
        String strX = "x";
        return strX.repeat(Math.max(0, length - 4)) + cardNo.substring(length - 4);
    }
}
