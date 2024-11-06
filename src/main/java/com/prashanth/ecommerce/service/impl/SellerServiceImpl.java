package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.SellerResponseDto;
import com.prashanth.ecommerce.entity.Product;
import com.prashanth.ecommerce.entity.Seller;
import com.prashanth.ecommerce.exception.*;
import com.prashanth.ecommerce.repository.SellerRepository;
import com.prashanth.ecommerce.service.ProductService;
import com.prashanth.ecommerce.service.SellerService;
import com.prashanth.ecommerce.transformer.SellerTransformer;
import com.prashanth.ecommerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    Validation validation;
    @Autowired
    ProductService productService;

    @Override
    public AddSellerResponseDto addSeller(AddSellerRequestDto addSellerRequestDto) throws EmailNotFoundException {
       if(sellerRepository.findByEmailId(addSellerRequestDto.getEmail()) != null)
           throw new EmailNotFoundException("Email ID already registered");
       //using transformer to convert AddSellerRequestDto to Seller class
        Seller seller = SellerTransformer.sellerRequestDtoToSeller((addSellerRequestDto));
        Seller savedSeller = sellerRepository.save(seller);
        //using transformer to convert Seller class to AddSellerResponseDto class
        return SellerTransformer.sellerToAddSellerResponseDto(savedSeller);
    }

    @Override
    public SellerResponseDto getSellerByEmail(String email) throws EmailNotFoundException {
        Seller seller = sellerRepository.findByEmailId(email);
        if(seller == null) throw new EmailNotFoundException("Seller emil ID is invalid");
        return SellerTransformer.sellerToSellerResponseDto(seller);
    }

    @Override
    public SellerResponseDto getSellerById(int id) throws SellerDoesNotExistException {
        validation.sellerValidation(id);
        Seller seller = sellerRepository.findById(id).get();
        return SellerTransformer.sellerToSellerResponseDto(seller);
    }

    @Override
    public List<SellerResponseDto> getAllSellers() throws SellerRepositoryEmptyException {
        List<Seller> sellerList = sellerRepository.findAll();
        if(sellerList.isEmpty()) throw new SellerRepositoryEmptyException("Seller repository is empty");
        return createSellerResponseDtoListFromSellerList(sellerList);
    }

    @Override
    public SellerResponseDto updateSellerNameByEmail(String email, String name) throws SellerDoesNotExistException {
        validation.sellerValidation(email);
        Seller seller = sellerRepository.findByEmailId(email);

        seller.setName(name);
        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }

    @Override
    public SellerResponseDto updateSellerAgeByEmail(String email, int age) throws SellerDoesNotExistException {
        validation.sellerValidation(email);
        Seller seller = sellerRepository.findByEmailId(email);

        seller.setAge(age);
        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }

    @Override
    public SellerResponseDto updateSellerNameById(int id, String name) throws SellerDoesNotExistException {
        validation.sellerValidation(id);
        Seller seller = sellerRepository.findById(id).get();

        seller.setName(name);
        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }

    @Override
    public SellerResponseDto updateSellerAgeById(int id, int age) throws SellerDoesNotExistException {
        validation.sellerValidation(id);
        Seller seller = sellerRepository.findById(id).get();

        seller.setAge(age);
        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }

    @Override
    public String deleteSellerByEmail(String email) throws SellerDoesNotExistException, ProductDoesNotExistException, CustomerDoestNotExistException, IncorrectSellerException, ItemNotFoundException {
        validation.sellerValidation(email);
        Seller seller = sellerRepository.findByEmailId(email);

        if(!seller.getProducts().isEmpty()){
            for(Product currProduct : seller.getProducts()){
                productService.deleteProduct(seller.getId(), currProduct.getId());
            }
        }
        sellerRepository.delete(seller);
        return "Seller deleted successfully";
    }

    @Override
    public String deleteSellerById(int id) throws SellerDoesNotExistException, ProductDoesNotExistException, CustomerDoestNotExistException, IncorrectSellerException, ItemNotFoundException {
        validation.sellerValidation(id);
        Seller seller = sellerRepository.findById(id).get();

        if(!seller.getProducts().isEmpty()){
            for(Product currProduct : seller.getProducts()){
                productService.deleteProduct(seller.getId(), currProduct.getId());
            }
        }
        sellerRepository.delete(seller);
        return "Seller deleted successfully";
    }

    @Override
    public List<SellerResponseDto> getAllSellersOfAge(int age) throws SellerDoesNotExistException {
        List<Seller> sellerList = sellerRepository.findAllByAge(age);
        if(sellerList.isEmpty()) throw new SellerDoesNotExistException("No seller of this age");
        return createSellerResponseDtoListFromSellerList(sellerList);
    }

    public List<SellerResponseDto> createSellerResponseDtoListFromSellerList(List<Seller> sellerList){
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller currSeller : sellerList){
            sellerResponseDtoList.add(SellerTransformer.sellerToSellerResponseDto(currSeller));
        }
        return sellerResponseDtoList;
    }
}
