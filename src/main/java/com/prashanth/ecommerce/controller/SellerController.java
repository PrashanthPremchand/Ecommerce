package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.SellerResponseDto;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    //Add a seller
    @PostMapping("/add_seller")
    public ResponseEntity addSeller(@RequestBody AddSellerRequestDto addSellerRequestDto) throws EmailNotFoundException {
        try{
            return new ResponseEntity(sellerService.addSeller(addSellerRequestDto), HttpStatus.CREATED);
        } catch (EmailNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Get a seller by email
    @GetMapping("/get_email")
    public SellerResponseDto getSellerByEmail(@RequestParam("email") String email) throws EmailNotFoundException {
        return sellerService.getSellerByEmail(email);
    }

    //Get by id
    @GetMapping("/get_id")
    public SellerResponseDto getSellerById(@RequestParam("id") int id) throws SellerDoesNotExistException {
        return sellerService.getSellerById(id);
    }

    //Get all seller
    @GetMapping("/get_all")
    public List<SellerResponseDto> getAllSellers() throws SellerRepositoryEmptyException {
        return sellerService.getAllSellers();
    }

    //Update seller name based on email id
    @PutMapping("/update_name_by_email")
    public SellerResponseDto updateSellerNameBYEmail(@RequestParam("email") String email, @RequestParam("name") String name) throws SellerDoesNotExistException {
        return sellerService.updateSellerNameByEmail(email, name);
    }

    //Update seller age based on email id
    @PutMapping("/update_age_by_email")
    public SellerResponseDto updateSellerAgeBYEmail(@RequestParam("email") String email, @RequestParam("age") int age) throws SellerDoesNotExistException {
        return sellerService.updateSellerAgeByEmail(email, age);
    }

    //Update seller name based on id
    @PutMapping("/update_name_by_id")
    public SellerResponseDto updateSellerNameBYEmail(@RequestParam("id") int id, @RequestParam("name") String name) throws SellerDoesNotExistException {
        return sellerService.updateSellerNameById(id, name);
    }

    //Update seller age based on id
    @PutMapping("/update_age_by_id")
    public SellerResponseDto updateSellerAgeBYEmail(@RequestParam("id") int id, @RequestParam("age") int age) throws SellerDoesNotExistException {
        return sellerService.updateSellerAgeById(id, age);
    }

    //Delete a seller by email
    @DeleteMapping("/delete_email")
    public String deleteSellerByEmail(@RequestParam("email") String email) throws ProductDoesNotExistException, CustomerDoestNotExistException, IncorrectSellerException, SellerDoesNotExistException, ItemNotFoundException {
        return sellerService.deleteSellerByEmail(email);
    }
}
