package com.prashanth.ecommerce.service.impl;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.entity.Seller;
import com.prashanth.ecommerce.exception.EmailNotFoundException;
import com.prashanth.ecommerce.repository.SellerRepository;
import com.prashanth.ecommerce.service.SellerService;
import com.prashanth.ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public AddSellerResponseDto addSeller(AddSellerRequestDto addSellerRequestDto) throws EmailNotFoundException {
       if(sellerRepository.findByEmailId(addSellerRequestDto.getEmail()) != null)
           throw new EmailNotFoundException("Email ID already registered");
       //using transformer to convert AddSellerRequestDto to Seller class
        Seller seller = SellerTransformer.sellerRequestDtoToSeller((addSellerRequestDto));
        Seller savedSeller = sellerRepository.save(seller);
        //using transformer to convert Seller class to AddSellerResponseDto class
        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }
}
