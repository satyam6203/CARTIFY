package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Model.VerificationCode;

public interface VerificationService {
    VerificationCode createVerificationCode(String otp, String email);
}
