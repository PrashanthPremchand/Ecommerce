package com.prashanth.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordered")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String orderNo;
    int totalValue;
    @CurrentTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date orderDate;
    String cardUsed;
    @ManyToOne
    @JoinColumn
    Customer customer;
    @OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
