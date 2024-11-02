package com.prashanth.ecommerce.entity;

import com.prashanth.ecommerce.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true, nullable = false)//it will only accept unique non-null entity
    String cardNo;
    int cvv;
    LocalDate expiryDate;
    @Enumerated(EnumType.STRING)
    CardType cardType;
    @ManyToOne
    @JoinColumn
    Customer customer;
}
