package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.AddCustomerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddCustomerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CustomerResponseDto;
import com.prashanth.ecommerce.entity.Cart;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.enums.CardType;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.exception.CustomerEmailIdAlreadyExistException;
import com.prashanth.ecommerce.exception.CustomerMobileNumberAlreadyExistException;
import com.prashanth.ecommerce.exception.CustomerRepositoryEmptyException;
import com.prashanth.ecommerce.repository.CustomerRepository;
import com.prashanth.ecommerce.service.CustomerService;
import com.prashanth.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public AddCustomerResponseDto addCustomer(AddCustomerRequestDto addCustomerRequestDto) throws CustomerEmailIdAlreadyExistException, CustomerMobileNumberAlreadyExistException {
        if(customerRepository.findByEmail(addCustomerRequestDto.getEmail()) != null)
            throw new CustomerEmailIdAlreadyExistException("Email ID already in use!");
        if(customerRepository.findByMobNo(addCustomerRequestDto.getMobNo()) != null)
            throw new CustomerMobileNumberAlreadyExistException("Mobile number already in use!");
        Customer customer = CustomerTransformer.addCustomerRequestDtoToCustomer(addCustomerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);//due to cascading this will save customer and all of its children(cart, card and order)

        return CustomerTransformer.customerToAddCustomerResponseDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() throws CustomerRepositoryEmptyException {
        List<Customer> customerList = customerRepository.findAll();
        if(customerList == null) throw new CustomerRepositoryEmptyException("Customer repository is empty");

        return createListOfCustomerResponseDtoFromListOfCustomers(customerList);
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerDoestNotExistException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null) throw new CustomerDoestNotExistException("Customer with this email ID does not exists");

        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto getCustomerByMobileNo(String mobNo) throws CustomerDoestNotExistException {
        Customer customer = customerRepository.findByMobNo(mobNo);
        if(customer == null) throw new CustomerDoestNotExistException("Customer with this mobile number does not exist");

        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getCustomerAboveAge(int age) throws CustomerDoestNotExistException {
        List<Customer> customerList = customerRepository.findAllByAge(age);
        if(customerList == null) throw new CustomerDoestNotExistException("No customers above this age");

        return createListOfCustomerResponseDtoFromListOfCustomers(customerList);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomerWithCardType(CardType cardType) throws CustomerDoestNotExistException {
        List<Customer> customerList = customerRepository.findAllByCardType(cardType);
        if(customerList == null) throw new CustomerDoestNotExistException("No customers with this card type");

        return createListOfCustomerResponseDtoFromListOfCustomers(customerList);
    }

    @Override
    public CustomerResponseDto updateCustomerNameWithEmail(String email, String name) throws CustomerDoestNotExistException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null) throw new CustomerDoestNotExistException("Customer with this email ID does not exists");

        customer.setName(name);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto updateCustomerNameWithMobNo(String mobNo, String name) throws CustomerDoestNotExistException {
        Customer customer = customerRepository.findByMobNo(mobNo);
        if(customer == null) throw new CustomerDoestNotExistException("Customer with this email ID does not exists");

        customer.setName(name);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public String deleteCustomerByEmail(String email) throws CustomerDoestNotExistException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null) throw new CustomerDoestNotExistException("Customer with this email ID does not exists");

        customerRepository.delete(customer);

        return "Customer deleted successfully";
    }

    @Override
    public String deleteCustomerByMobNo(String mobNo) throws CustomerDoestNotExistException {
        Customer customer = customerRepository.findByMobNo(mobNo);
        if(customer == null) throw new CustomerDoestNotExistException("Customer with this email ID does not exists");

        customerRepository.delete(customer);

        return "Customer deleted successfully";
    }

    public List<CustomerResponseDto> createListOfCustomerResponseDtoFromListOfCustomers(List<Customer> customerList){
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer currCustomer : customerList){
            customerResponseDtoList.add(CustomerTransformer.customerToCustomerResponseDto(currCustomer));
        }
        return customerResponseDtoList;
    }
}
