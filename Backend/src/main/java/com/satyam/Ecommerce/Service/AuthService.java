package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Request.LoginRequest;
import com.satyam.Ecommerce.Response.AuthResponse;
import com.satyam.Ecommerce.Response.SignUpRequest;
import com.satyam.Ecommerce.constants.USER_ROLE;

public interface AuthService {
    String createUser(SignUpRequest req) throws Exception;

    void sendLoginOtp(String email, USER_ROLE role) throws Exception;
    AuthResponse signing(LoginRequest request) throws Exception;
}
