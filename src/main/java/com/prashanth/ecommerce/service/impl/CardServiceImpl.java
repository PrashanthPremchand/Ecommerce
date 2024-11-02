package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.AddCardRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CardResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.entity.Card;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.enums.CardType;
import com.prashanth.ecommerce.exception.CardAlreadyExistException;
import com.prashanth.ecommerce.exception.CardExpiredException;
import com.prashanth.ecommerce.exception.CardNotFoundException;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.repository.CardRepository;
import com.prashanth.ecommerce.repository.CustomerRepository;
import com.prashanth.ecommerce.service.CardService;
import com.prashanth.ecommerce.transformer.CardTransformer;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public AddCardResponseDto addCard(AddCardRequestDto addCardRequestDto) throws CustomerDoestNotExistException, CardExpiredException, CardAlreadyExistException {
        Customer customer;
        try{
            customer = customerRepository.findByMobNo(addCardRequestDto.getMobNo());
        } catch(Exception e){
            throw new CustomerDoestNotExistException("Customer mobile number is wrong!");
        }
        LocalDate now = LocalDate.now();
        if(addCardRequestDto.getExpiryDate().isBefore(now))
            throw new CardExpiredException("Your card has expired!");
        if(cardRepository.findByCardNo(addCardRequestDto.getCardNo()) != null)
            throw new CardAlreadyExistException("This already exist.");
        Card card = CardTransformer.addCardRequestDtoToCard(addCardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        customerRepository.save(customer);//due to cascading this will save customer and all of its children(cart, card and order)

        return CardTransformer.cardToAddCardResponseDto(card);
    }

    @Override
    public List<CardResponseDto> findCardByType(CardType cardType) throws CardNotFoundException {
        List<Card> cardList = cardRepository.findAllByCardType(cardType);
        if(cardList == null) throw new CardNotFoundException("There are no cards with this card type in the database");
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card currCard : cardList){
            cardResponseDtoList.add(CardTransformer.cardToCardResponseDto(currCard));
        }
        return cardResponseDtoList;
    }

    @Override
    public List<CardResponseDto> findCardByTypeAndExpiry(CardType cardType, LocalDate expiryDate) throws CardNotFoundException {
        List<Card> cardList = cardRepository.findAllByCardTypeAndExpiryDate(cardType, expiryDate);
        if(cardList == null) throw new CardNotFoundException("There are no card matching you criteria");
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card currCard : cardList){
            cardResponseDtoList.add(CardTransformer.cardToCardResponseDto(currCard));
        }
        return cardResponseDtoList;
    }

    @Override
    public CardType findCardTypeWithMaxCards() throws CardNotFoundException {
        CardType cardType = cardRepository.findCardTypeWithMaxCount();
        if(cardType ==  null) throw new CardNotFoundException("No card in the database");
        return cardType;
    }
}
