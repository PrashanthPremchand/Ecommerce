package com.prashanth.ecommerce.validation;

import com.prashanth.ecommerce.entity.*;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.repository.*;
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
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    SellerRepository sellerRepository;

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

    public void orderValidation(int orderId) throws OrderDoesNotExist {
        try{
            Ordered ordered = orderedRepository.findById(orderId).get();
        }
        catch (Exception e){
            throw new OrderDoesNotExist("Invalid order ID");
        }
    }

    public void sellerValidation(String email) throws SellerDoesNotExistException {
        try{
            Seller seller = sellerRepository.findByEmailId(email);
        } catch (Exception e){
            throw new SellerDoesNotExistException("Invalid seller email ID");
        }
    }

    public void sellerValidation(int sellerId) throws SellerDoesNotExistException {
        try{
            Seller seller = sellerRepository.findById(sellerId).get();
        } catch (Exception e){
            throw new SellerDoesNotExistException("Invalid seller email ID");
        }
    }
}
