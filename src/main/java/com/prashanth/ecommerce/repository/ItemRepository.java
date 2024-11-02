package com.prashanth.ecommerce.repository;

import com.prashanth.ecommerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
