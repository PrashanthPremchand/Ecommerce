package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.AddCardRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.enums.CardType;
import com.prashanth.ecommerce.exception.CardAlreadyExistException;
import com.prashanth.ecommerce.exception.CardExpiredException;
import com.prashanth.ecommerce.exception.CardNotFoundException;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;

import java.time.LocalDate;
import java.util.List;

public interface CardService {
    public AddCardResponseDto addCard(AddCardRequestDto addCardRequestDto) throws CustomerDoestNotExistException, CardExpiredException, CardAlreadyExistException;
    public List<CardResponseDto> findCardByType(CardType cardType) throws CardNotFoundException;
    public List<CardResponseDto> findCardByTypeAndExpiry(CardType cardType, LocalDate expiryDate) throws CardNotFoundException;
    public CardType findCardTypeWithMaxCards() throws CardNotFoundException;
}
