package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.exception.SellerDoesNotExistException;
import com.prashanth.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add_product")
    public AddProductResponseDto addProduct(@RequestBody AddProductRequestDto addProductRequestDto) throws SellerDoesNotExistException {
        return productService.addProduct(addProductRequestDto);
    }
}
