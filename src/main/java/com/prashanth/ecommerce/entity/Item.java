package com.prashanth.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int requiredQuantity;
    @ManyToOne
    @JoinColumn
    Product product;
    @ManyToOne
    @JoinColumn
    Ordered ordered;
    @ManyToOne
    @JoinColumn
    Cart cart;
}
