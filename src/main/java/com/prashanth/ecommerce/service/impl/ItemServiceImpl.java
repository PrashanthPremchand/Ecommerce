package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.ItemRequestDto;
import com.prashanth.ecommerce.entity.Customer;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.enums.ProductStatus;
import com.prashanth.ecommerce.exception.CustomerDoestNotExistException;
import com.prashanth.ecommerce.exception.ProductDoesNotExistException;
import com.prashanth.ecommerce.exception.ProductOutOfStockException;
import com.prashanth.ecommerce.repository.CustomerRepository;
import com.prashanth.ecommerce.repository.ItemRepository;
import com.prashanth.ecommerce.repository.ProductRepository;
import com.prashanth.ecommerce.service.ItemService;
import com.prashanth.ecommerce.transformer.ItemTransformer;
import com.prashanth.ecommerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    Validation validation;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws CustomerDoestNotExistException, ProductDoesNotExistException, ProductOutOfStockException {
        validation.customerValidation(itemRequestDto.getCustomerId());
        Customer customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        validation.productValidation(itemRequestDto.getProductId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();
        if(product.getProductStatus() != ProductStatus.AVAILABLE || itemRequestDto.getRequiredQuantity() > product.getQuantity()){
            throw new ProductOutOfStockException("Product out of stock");
        }

        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto);
        item.setProduct(product);

        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}
