package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ProductResponseDto;
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
    public List<ProductResponseDto> getAllProductBySellerEmail(@RequestParam("email")String email) throws SellerDoesNotExistException {
        return productService.getAllProductBySellerEmail(email);
    }

    //Delete a product by seller ID and product ID
    @DeleteMapping("/delete_product")
    public String deleteProduct(@RequestParam("sellerId")int sellerId, @RequestParam("productId")int productId) throws ProductDoesNotExistException, IncorrectSellerException, SellerDoesNotExistException, CustomerDoestNotExistException, ItemNotFoundException {
        return productService.deleteProduct(sellerId, productId);
    }

    //Return top 5 cheapest products
    @GetMapping("/five_chepest_products")

}
