package com.prashanth.ecommerce.repository;

import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.entity.Ordered;
import com.prashanth.ecommerce.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Customer findByMobNo(String mobNo);
    @Query("SELECT c FROM Customer c WHERE c.age > :age")
    List<Customer> findAllByAge(int age);
    @Query("SELECT DISTINCT c FROM Customer c JOIN c.cards card WHERE card.cardType = :cardType")
    List<Customer> findAllByCardType(CardType cardType);
}
