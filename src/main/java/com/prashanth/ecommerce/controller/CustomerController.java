package com.prashanth.ecommerce.controller;

import com.prashanth.ecommerce.dto.requestdto.AddCustomerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCustomerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CustomerResponseDto;
import com.prashanth.ecommerce.enums.CardType;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.exception.CustomerEmailIdAlreadyExistException;
import com.prashanth.ecommerce.exception.CustomerMobileNumberAlreadyExistException;
import com.prashanth.ecommerce.exception.CustomerRepositoryEmptyException;
import com.prashanth.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //Add a customer
    @PostMapping("/add_customer")
    public AddCustomerResponseDto addCustomer(@RequestBody AddCustomerRequestDto addCustomerRequestDto) throws CustomerEmailIdAlreadyExistException, CustomerMobileNumberAlreadyExistException {
        return customerService.addCustomer(addCustomerRequestDto);
    }

    //View all customers
    @GetMapping("/get_all_customers")
    public List<CustomerResponseDto> getAllCustomers() throws CustomerRepositoryEmptyException {
        return customerService.getAllCustomers();
    }

    //Find a customer by email
    @GetMapping("/get_email")
    public CustomerResponseDto getCustomerByEmail(@RequestParam("email") String email) throws CustomerDoestNotExistException {
        return customerService.getCustomerByEmail(email);
    }

    //Find customer by mobile number
    @GetMapping("/get_mobileNo")
    public CustomerResponseDto getCustomerByMobilNo(@RequestParam("mobNo")String mobNo) throws CustomerDoestNotExistException {
        return customerService.getCustomerByMobileNo(mobNo);
    }

    //Find all customer whose age is greater than a certain age
    @GetMapping("/get_all_above_age")
    public List<CustomerResponseDto> getCustomerAboveAge(@RequestParam("age")int age) throws CustomerDoestNotExistException {
        return customerService.getCustomerAboveAge(age);
    }

    //Find all customer with a card type
    @GetMapping("/get_all_by_cardType")
    public List<CustomerResponseDto> getAllCustomerWithCardType(@RequestParam("cardType") CardType cardType) throws CustomerDoestNotExistException {
        return customerService.getAllCustomerWithCardType(cardType);
    }

    //Update customer name using customer email
    @PutMapping("/update_name_with_email")
    public CustomerResponseDto updateCustomerNameWithEmail(@RequestParam("email")String email, @RequestParam("name")String name) throws CustomerDoestNotExistException {
        return customerService.updateCustomerNameWithEmail(email, name);
    }

    //Update customer name using customer mobile number
    @PutMapping("/update_name_with_mobNo")
    public CustomerResponseDto updateCustomerNameWithMobNo(@RequestParam("mobNo")String mobNo, @RequestParam("name")String name) throws CustomerDoestNotExistException {
        return customerService.updateCustomerNameWithMobNo(mobNo, name);
    }

    // delete a customer by email
    @DeleteMapping("/delete_by_email")
    public String deleteCustomerByEmail(@RequestParam("email")String email) throws CustomerDoestNotExistException {
        return customerService.deleteCustomerByEmail(email);
    }

    //delete a customer by mobile number
    @DeleteMapping("/delete_by_mobNo")
    public String deleteCustomerByMobNo(@RequestParam("mobNo")String mobNo) throws CustomerDoestNotExistException {
        return customerService.deleteCustomerByMobNo(mobNo);
    }
}
