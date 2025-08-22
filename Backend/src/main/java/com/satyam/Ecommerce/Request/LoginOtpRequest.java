package com.satyam.Ecommerce.Request;

import com.satyam.Ecommerce.constants.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}
