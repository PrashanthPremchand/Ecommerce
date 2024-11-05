package com.prashanth.ecommerce.repository;

import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.enums.ProductCategory;
import com.prashanth.ecommerce.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product ORDER BY price ASC LIMIT 5", nativeQuery = true)
    List<Product> findFiveCheapest();
    @Query(value = "SELECT * FROM product ORDER BY price DESC LIMIT 5", nativeQuery = true)
    List<Product> findFiveCostliest();
    List<Product> findAllByProductStatus(ProductStatus productStatus);
    @Query("SELECT p FROM Product p WHERE p.quantity < :limit")
    List<Product> findAllLessThanQuantity(@Param("limit") int limit);
    @Query(value = "SELECT * FROM product WHERE product_category = :productCategory ORDER BY price ASC LIMIT 1", nativeQuery = true)
    Product findCheapestProductCategory(@Param("productCategory") String productCategory);
    @Query(value = "SELECT * FROM product WHERE product_category = :productCategory ORDER BY price DESC LIMIT 1", nativeQuery = true)
    Product findCostliestProductCategory(@Param("productCategory") String productCategory);
}
