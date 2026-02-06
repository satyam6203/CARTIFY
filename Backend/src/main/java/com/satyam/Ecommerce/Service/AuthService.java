package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Exceptions.SellerException;
import com.satyam.Ecommerce.Exceptions.UserException;
import com.satyam.Ecommerce.Request.LoginRequest;
import com.satyam.Ecommerce.Request.SignupRequest;
import com.satyam.Ecommerce.Response.AuthResponse;

import jakarta.mail.MessagingException;


public interface AuthService {

    void sentLoginOtp(String email) throws UserException, MessagingException;
    String createUser(SignupRequest req) throws SellerException;
    AuthResponse signin(LoginRequest req) throws SellerException;

}
