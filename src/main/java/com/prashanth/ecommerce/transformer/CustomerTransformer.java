package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.requestdto.AddCustomerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCustomerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CustomerResponseDto;
import com.prashanth.ecommerce.entity.Card;
import com.prashanth.ecommerce.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerTransformer {
    private static final Logger log = LoggerFactory.getLogger(CustomerTransformer.class);

    public static Customer addCustomerRequestDtoToCustomer(AddCustomerRequestDto addCustomerRequestDto){
        return Customer.builder()
                .name(addCustomerRequestDto.getName())
                .email(addCustomerRequestDto.getEmail())
                .age(addCustomerRequestDto.getAge())
                .mobNo(addCustomerRequestDto.getMobNo())
                .address(addCustomerRequestDto.getAddress())
                .build();
    }

    public static AddCustomerResponseDto customerToAddCustomerResponseDto(Customer customer){
        return AddCustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome " + customer.getName() + " to the world of online shopping!" )
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card currCard : customer.getCards()){
            cardResponseDtoList.add(CardTransformer.cardToCardResponseDto(currCard));
        }

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .age(customer.getAge())
                .mobNo(customer.getMobNo())
                .email(customer.getEmail())
                .cardResponseDtoList(cardResponseDtoList)
                .build();
    }
}
