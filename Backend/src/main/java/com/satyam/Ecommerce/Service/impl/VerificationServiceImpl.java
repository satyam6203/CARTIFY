package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Model.VerificationCode;
import com.satyam.Ecommerce.Repo.VerificationRepo;
import com.satyam.Ecommerce.Service.VerificationService;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationRepo verificationCodeRepository;

    VerificationServiceImpl(VerificationRepo verificationCodeRepository){

        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public VerificationCode createVerificationCode(String otp,String email) {
        VerificationCode isExist=verificationCodeRepository.findByEmail(email);

        if(isExist!=null) {
            verificationCodeRepository.delete(isExist);
        }

        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        return verificationCodeRepository.save(verificationCode);

    }
}

