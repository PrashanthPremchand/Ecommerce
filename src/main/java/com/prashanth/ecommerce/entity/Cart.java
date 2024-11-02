package com.prashanth.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int cartTotal;
    int numberOfItems;
    @OneToOne
    @JoinColumn
    Customer customer;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
