package com.prashanth.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(unique = true)
    String email;
    int age;
    @Column(unique = true)
    String mobNo;
    String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cards;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderedList = new ArrayList<>();
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;
}
