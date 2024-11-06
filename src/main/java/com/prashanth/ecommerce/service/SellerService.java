package com.prashanth.ecommerce.service;

import com.prashanth.ecommerce.dto.requestdto.AddSellerRequestDto;
import com.prashanth.ecommerce.dto.responsedto.AddSellerResponseDto;
import com.prashanth.ecommerce.dto.responsedto.SellerResponseDto;
import com.prashanth.ecommerce.exception.*;

import java.util.List;

public interface SellerService {
    public AddSellerResponseDto addSeller(AddSellerRequestDto addSellerRequestDto) throws EmailNotFoundException;
    public SellerResponseDto getSellerByEmail(String email) throws EmailNotFoundException;
    public SellerResponseDto getSellerById(int id) throws SellerDoesNotExistException;
    public List<SellerResponseDto> getAllSellers() throws SellerRepositoryEmptyException;
    public SellerResponseDto updateSellerNameByEmail(String email, String name) throws SellerDoesNotExistException;
    public SellerResponseDto updateSellerAgeByEmail(String email, int age) throws SellerDoesNotExistException;
    public SellerResponseDto updateSellerNameById(int id, String name) throws SellerDoesNotExistException;
    public SellerResponseDto updateSellerAgeById(int id, int age) throws SellerDoesNotExistException;
    public String deleteSellerByEmail(String email) throws SellerDoesNotExistException, ProductDoesNotExistException, CustomerDoestNotExistException, IncorrectSellerException, ItemNotFoundException;
    public String deleteSellerById(int id) throws SellerDoesNotExistException, ProductDoesNotExistException, CustomerDoestNotExistException, IncorrectSellerException, ItemNotFoundException;
    public List<SellerResponseDto> getAllSellersOfAge(int age) throws SellerDoesNotExistException;
}
