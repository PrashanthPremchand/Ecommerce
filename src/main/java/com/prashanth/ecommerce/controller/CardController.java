package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddCardRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.enums.CardType;
import com.prashanth.ecommerce.exception.CardAlreadyExistException;
import com.prashanth.ecommerce.exception.CardExpiredException;
import com.prashanth.ecommerce.exception.CardNotFoundException;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    //Add a card
    @PostMapping("/add_card")
    public AddCardResponseDto addCard(@RequestBody AddCardRequestDto addCardRequestDto) throws CustomerDoestNotExistException, CardAlreadyExistException, CardExpiredException {
        return cardService.addCard(addCardRequestDto);
    }

    //Find a card by its type
    @GetMapping("/get_card_by_type")
    public List<CardResponseDto> findCardByType(@RequestParam("type")CardType cardType) throws CardNotFoundException {
        return cardService.findCardByType(cardType);
    }

    //Find a card by its type and expiry date
    @GetMapping("/get_card_by_type_and_expiry_date")
    public List<CardResponseDto> findCardBYTypeAndExpiry(@RequestParam("type")CardType cardType, @RequestParam("expiryDate")LocalDate expiryDate) throws CardNotFoundException {
        return cardService.findCardByTypeAndExpiry(cardType, expiryDate);
    }

    //Find the card type with maximum cards
    @GetMapping("/get_cardType_with_max_cards")
    public CardType findCardTypeWithMaxCards() throws CardNotFoundException {
        return cardService.findCardTypeWithMaxCards();
    }
}
