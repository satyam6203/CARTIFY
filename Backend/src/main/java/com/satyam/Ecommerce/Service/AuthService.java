package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Response.SignUpRequest;

public interface AuthService {
    String createUser(SignUpRequest req);
}
