package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.AddProductRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddProductResponseDto;
import com.prashanth.ecommerce.dto.responsedto.CartResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ItemResponseDto;
import com.prashanth.ecommerce.dto.responsedto.ProductResponseDto;
import com.prashanth.ecommerce.entity.*;
import com.prashanth.ecommerce.enums.ProductCategory;
import com.prashanth.ecommerce.enums.ProductStatus;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.repository.*;
import com.prashanth.ecommerce.service.CartService;
import com.prashanth.ecommerce.service.ProductService;
import com.prashanth.ecommerce.transformer.CartTransformer;
import com.prashanth.ecommerce.transformer.ItemTransformer;
import com.prashanth.ecommerce.transformer.ProductTransformer;
import com.prashanth.ecommerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
//    @Autowired
//    CustomerRepository customerRepository;
//    @Autowired
//    ItemRepository itemRepository;
//    @Autowired
//    CartRepository cartRepository;
//    @Autowired
//    CartService cartService;
    @Autowired
    Validation validation;

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

    public void decreaseProductQuantity(Item item) throws ProductOutOfStockException, ProductDoesNotExistException {
        Product product = item.getProduct();
        if(product == null) throw new ProductDoesNotExistException("For item " + item.getId() + " product doest not exist");
        int requiredQuantity = item.getRequiredQuantity();
        int availableQuantity = product.getQuantity();
        if(availableQuantity  < requiredQuantity){
           throw new ProductOutOfStockException("Product out of stock");
        }
        product.setQuantity(availableQuantity - requiredQuantity);
        if(product.getQuantity() == 0) product.setProductStatus(ProductStatus.OUT_OF_STOCK);
    }

    @Override
    public List<ProductResponseDto> getAllProductBySellerEmail(String email) throws SellerDoesNotExistException {
        validation.sellerValidation(email);
        Seller seller = sellerRepository.findByEmailId(email);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product currProduct : seller.getProducts()){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(currProduct));
        }

        return productResponseDtoList;
    }

    @Override
    public String deleteProduct(int sellerId, int productId) throws ProductDoesNotExistException, SellerDoesNotExistException, IncorrectSellerException, CustomerDoestNotExistException, ItemNotFoundException {
        validation.productValidation(productId);
        Product product = productRepository.findById(productId).get();

        validation.sellerValidation(sellerId);
        Seller seller = sellerRepository.findById(sellerId).get();

        if(product.getSeller() != seller) throw new IncorrectSellerException("Seller is not a match for this product");

        seller.getProducts().remove(product);

        for(Item currItem : product.getItemList()){
            currItem.setProduct(null);
        }
        productRepository.delete(product);

        return "Product has been successfully deleted";
    }

    @Override
    public List<ProductResponseDto> getFiveCheapestProducts() throws ProductRepositoryEmptyException {
        List<Product> productList = productRepository.findFiveCheapest();
        if(productList == null) throw new ProductRepositoryEmptyException("Product repository is empty");
        return createListOfProductResponseDtoFromListOfProducts(productList);
    }

    @Override
    public List<ProductResponseDto> getFiveCostliestProducts() throws ProductRepositoryEmptyException {
        List<Product> productList = productRepository.findFiveCostliest();
        if(productList == null) throw new ProductRepositoryEmptyException("Product repository is empty");
        return createListOfProductResponseDtoFromListOfProducts(productList);
    }

    @Override
    public List<ProductResponseDto> getAllOutOfStockProducts() throws ProductDoesNotExistException {
        List<Product> productList = productRepository.findAllByProductStatus(ProductStatus.OUT_OF_STOCK);
        if(productList.isEmpty()) throw new ProductDoesNotExistException("No product is out of stock");
        return createListOfProductResponseDtoFromListOfProducts(productList);
    }

    @Override
    public List<ProductResponseDto> getAllAvailableProducts() throws ProductDoesNotExistException {
        List<Product> productList = productRepository.findAllByProductStatus(ProductStatus.AVAILABLE);
        if(productList.isEmpty()) throw new ProductDoesNotExistException("No product is available");
        return createListOfProductResponseDtoFromListOfProducts(productList);
    }

    @Override
    public List<ProductResponseDto> getAllProductQuantityLessThanLimit(int limit) throws ProductDoesNotExistException {
        List<Product> productList = productRepository.findAllLessThanQuantity(limit);
        if(productList.isEmpty()) throw new ProductDoesNotExistException("No product is less than the specified limit");
        return createListOfProductResponseDtoFromListOfProducts(productList);
    }

    @Override
    public ProductResponseDto getCheapestInCategory(String productCategory) throws ProductDoesNotExistException {
        Product product = productRepository.findCheapestProductCategory(productCategory);
        if(product == null) throw new ProductDoesNotExistException("No product in this product category");
        return ProductTransformer.productToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto getCostliestInCategory(String productCategory) throws ProductDoesNotExistException {
        Product product = productRepository.findCostliestProductCategory(productCategory);
        if(product == null) throw new ProductDoesNotExistException("No product in this product category");
        return ProductTransformer.productToProductResponseDto(product);
    }

    public List<ProductResponseDto> createListOfProductResponseDtoFromListOfProducts(List<Product> productList){
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product currProduct : productList){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(currProduct));
        }
        return productResponseDtoList;
    }
}
