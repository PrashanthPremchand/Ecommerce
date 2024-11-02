package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.entity.Item;
import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.entity.Seller;
import com.prashanth.ecommerce.enums.ProductStatus;
import com.prashanth.ecommerce.exception.ProductOutOfStockException;
import com.prashanth.ecommerce.exception.SellerDoesNotExistException;
import com.prashanth.ecommerce.repository.SellerRepository;
import com.prashanth.ecommerce.service.ProductService;
import com.prashanth.ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public AddProductResponseDto addProduct(AddProductRequestDto addProductRequestDto) throws SellerDoesNotExistException {
        Seller seller;
        try{
            seller = sellerRepository.findById(addProductRequestDto.getSellerId()).get();
        } catch (Exception e){
            throw new SellerDoesNotExistException("Seller does not exist");
        }
        Product product = ProductTransformer.addProductRequestDtoToProduct(addProductRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);
        sellerRepository.save(seller);
        return ProductTransformer.productToAddProductResponseDto(product);
    }

    public void decreaseProductQuantity(Item item) throws ProductOutOfStockException {
        Product product = item.getProduct();
        int requiredQuantity = item.getRequiredQuantity();
        int availableQuantity = product.getQuantity();
        if(availableQuantity  < requiredQuantity){
           throw new ProductOutOfStockException("Product out of stock");
        }
        product.setQuantity(availableQuantity - requiredQuantity);
        if(product.getQuantity() == 0) product.setProductStatus(ProductStatus.OUT_OF_STOCK);
    }
}
