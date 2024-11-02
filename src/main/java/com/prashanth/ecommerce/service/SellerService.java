package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.exception.EmailNotFoundException;

public interface SellerService {
    public AddSellerResponseDto addSeller(AddSellerRequestDto addSellerRequestDto) throws EmailNotFoundException;
}
