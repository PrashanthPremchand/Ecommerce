package com.prashanth.ecommerce.repository;

import com.prashanth.ecommerce.entity.Ordered;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
    @Query(value = "SELECT * FROM ordered WHERE customer_id = :customerId ORDER BY order_date DESC LIMIT 5", nativeQuery = true)
    List<Ordered> findByCustomer_CustomerId(int customerId);
}
