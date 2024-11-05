package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ProductResponseDto;
import com.prashanth.ecommerce.enums.ProductCategory;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    //Add a product
    @PostMapping("/add_product")
    public AddProductResponseDto addProduct(@RequestBody AddProductRequestDto addProductRequestDto) throws SellerDoesNotExistException {
        return productService.addProduct(addProductRequestDto);
    }

    //Get all the product by seller email
    @GetMapping("/get_all_by_email")
    public List<ProductResponseDto> getAllProductBySellerEmail(@RequestParam("email") String email) throws SellerDoesNotExistException {
        return productService.getAllProductBySellerEmail(email);
    }

    //Delete a product by seller ID and product ID
    @DeleteMapping("/delete_product")
    public String deleteProduct(@RequestParam("sellerId") int sellerId, @RequestParam("productId") int productId) throws ProductDoesNotExistException, IncorrectSellerException, SellerDoesNotExistException, CustomerDoestNotExistException, ItemNotFoundException {
        return productService.deleteProduct(sellerId, productId);
    }

    //Return top 5 cheapest products
    @GetMapping("/five_cheapest_products")
    public List<ProductResponseDto> getFiveCheapestProducts() throws ProductRepositoryEmptyException {
        return productService.getFiveCheapestProducts();
    }

    //Return top 5 costliest products
    @GetMapping("/five_costliest_products")
    public List<ProductResponseDto> getFiveCostliestProducts() throws ProductRepositoryEmptyException {
        return productService.getFiveCostliestProducts();
    }

    //Return all out-of-stock products
    @GetMapping("/get_all_out_of_stock_products")
    public List<ProductResponseDto> getAllOutOfStockProducts() throws ProductDoesNotExistException {
        return productService.getAllOutOfStockProducts();
    }

    //Return all out-of-stock products
    @GetMapping("/get_all_available_products")
    public List<ProductResponseDto> getAvailableProducts() throws ProductDoesNotExistException {
        return productService.getAllAvailableProducts();
    }

    //Return all products that have quantity less than value
    @GetMapping("/get_all_products_less_than_limit")
    public List<ProductResponseDto> getAllProductsQuantityLessThanLimit(@RequestParam("limit") int limit) throws ProductDoesNotExistException {
        return productService.getAllProductQuantityLessThanLimit(limit);
    }

    //Return the cheapest product in a particular category
    @GetMapping("/get_cheapest_category/{productCategory}")
    public ProductResponseDto getCheapestInCategory(@PathVariable String productCategory) throws ProductDoesNotExistException {
        return productService.getCheapestInCategory(productCategory);
    }

    //Return the costliest product in a particular category
    @GetMapping("/get_costliest_category/{productCategory}")
    public ProductResponseDto getCostliestInCategory(@PathVariable String productCategory) throws ProductDoesNotExistException {
        return productService.getCostliestInCategory(productCategory);
    }
}
