package com.prashanth.ecommerce.entity;

import com.prashanth.ecommerce.enums.ProductCategory;
import com.prashanth.ecommerce.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int price;
    int quantity;
    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;
    @ManyToOne
    @JoinColumn
    Seller seller;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
}
