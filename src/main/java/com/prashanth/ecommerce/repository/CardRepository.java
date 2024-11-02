package com.prashanth.ecommerce.repository;

import com.prashanth.ecommerce.entity.Card;
import com.prashanth.ecommerce.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardNo(String cardNo);
    List<Card> findAllByCardType(CardType cardType);
    @Query(value = "SELECT c FROM Card c WHERE c.cardType = :cardType AND c.expiryDate > :expiryDate")
    List<Card> findAllByCardTypeAndExpiryDate(CardType cardType, LocalDate expiryDate);
    @Query(value = "SELECT card_type FROM card  GROUP BY card_type ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    CardType findCardTypeWithMaxCount();
}
