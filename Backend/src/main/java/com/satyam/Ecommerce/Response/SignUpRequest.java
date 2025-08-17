package com.satyam.Ecommerce.Response;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String otp;
    private String fullName;
}
