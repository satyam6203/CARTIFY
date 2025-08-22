package com.satyam.Ecommerce.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String otp;
}
