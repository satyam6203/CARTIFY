package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Exceptions.SellerException;
import com.satyam.Ecommerce.constants.AccountStatus;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerByID(Long id) throws SellerException;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSellers(AccountStatus status);
    Seller updateSeller(Long id,Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller verifyEmail(String email,String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerID,AccountStatus status) throws Exception;
}
