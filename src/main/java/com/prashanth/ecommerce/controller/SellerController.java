package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.exception.EmailNotFoundException;
import com.prashanth.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add_seller")
    public ResponseEntity addSeller(@RequestBody AddSellerRequestDto addSellerRequestDto) throws EmailNotFoundException {
        try{
            return new ResponseEntity(sellerService.addSeller(addSellerRequestDto), HttpStatus.CREATED);
        } catch (EmailNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
