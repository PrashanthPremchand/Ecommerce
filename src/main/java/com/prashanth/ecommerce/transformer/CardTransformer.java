package com.prashanth.ecommerce.transformer;

import com.prashanth.ecommerce.dto.requestdto.AddCardRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.entity.Card;

public class CardTransformer {
    public static Card addCardRequestDtoToCard(AddCardRequestDto addCardRequestDto){
        return Card.builder()
                .cardNo(addCardRequestDto.getCardNo())
                .cardType(addCardRequestDto.getCardType())
                .cvv(addCardRequestDto.getCvv())
                .expiryDate(addCardRequestDto.getExpiryDate())
                .build();
    }
    public static AddCardResponseDto cardToAddCardResponseDto(Card card){
        return AddCardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .build();
    }
    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .expiryDate(card.getExpiryDate())
                .cardType(card.getCardType())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
