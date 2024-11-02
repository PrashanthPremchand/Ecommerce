package com.prashanth.ecommerce.validation;

import com.prashanth.ecommerce.entity.Card;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.exception.InvalidCardException;
import com.prashanth.ecommerce.exception.ItemNotFoundException;
import com.prashanth.ecommerce.exception.ProductDoesNotExistException;
import com.prashanth.ecommerce.repository.CardRepository;
import com.prashanth.ecommerce.repository.CustomerRepository;
import com.prashanth.ecommerce.repository.ItemRepository;
import com.prashanth.ecommerce.repository.ProductRepository;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validation {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;

    public void customerValidation(int customerId) throws CustomerDoestNotExistException {
        try{
            Customer customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerDoestNotExistException("Invalid customer ID");
        }
    }

    public void productValidation(int productId) throws ProductDoesNotExistException {
        try{
            Product product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductDoesNotExistException("Invalid product ID");
        }
    }

    public void itemValidation(int itemId) throws ItemNotFoundException {
        try{
            Item item = itemRepository.findById(itemId).get();
        }
        catch (Exception e){
            throw new ItemNotFoundException("Invalid item ID");
        }
    }
}
