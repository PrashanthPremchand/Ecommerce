package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.AddCustomerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCustomerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CustomerResponseDto;
import com.prashanth.ecommerce.enums.CardType;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.exception.CustomerEmailIdAlreadyExistException;
import com.prashanth.ecommerce.exception.CustomerMobileNumberAlreadyExistException;
import com.prashanth.ecommerce.exception.CustomerRepositoryEmptyException;

import java.util.List;

public interface CustomerService {
    public AddCustomerResponseDto addCustomer(AddCustomerRequestDto addCustomerRequestDto) throws CustomerEmailIdAlreadyExistException, CustomerMobileNumberAlreadyExistException;
    public List<CustomerResponseDto> getAllCustomers() throws CustomerRepositoryEmptyException;
    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerDoestNotExistException;
    public CustomerResponseDto getCustomerByMobileNo(String mobNo) throws CustomerDoestNotExistException;
    public List<CustomerResponseDto> getCustomerAboveAge(int age) throws CustomerDoestNotExistException;
    public List<CustomerResponseDto> getAllCustomerWithCardType(CardType cardType) throws CustomerDoestNotExistException;
    public CustomerResponseDto updateCustomerNameWithEmail(String email, String name) throws CustomerDoestNotExistException;
    public CustomerResponseDto updateCustomerAgeWithEmail(String email, int age) throws CustomerDoestNotExistException;
    public CustomerResponseDto updateCustomerAddressWithEmail(String email, String address) throws CustomerDoestNotExistException;
    public CustomerResponseDto updateCustomerNameWithMobNo(String mobNo, String name) throws CustomerDoestNotExistException;
    public CustomerResponseDto updateCustomerAgeWithMobNo(String mobNo, int age) throws CustomerDoestNotExistException;
    public CustomerResponseDto updateCustomerAddressWithMobNo(String mobNo, String address) throws CustomerDoestNotExistException;
    public String deleteCustomerByEmail(String email) throws CustomerDoestNotExistException;
    public String deleteCustomerByMobNo(String mobNo) throws CustomerDoestNotExistException;
}
