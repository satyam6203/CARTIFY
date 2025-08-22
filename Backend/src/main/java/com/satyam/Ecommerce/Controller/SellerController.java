package com.satyam.Ecommerce.Controller;

import com.satyam.Ecommerce.Domain.VerificationCode;
import com.satyam.Ecommerce.Repo.VerificationRepo;
import com.satyam.Ecommerce.Request.LoginRequest;
import com.satyam.Ecommerce.Response.AuthResponse;
import com.satyam.Ecommerce.Service.AuthService;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.impl.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {

    private  final SellerService sellerService;
    private final VerificationRepo verificationRepo;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception {

        String otp=req.getOtp();
        String email=req.getEmail();

//        VerificationCode verificationCode=verificationRepo.findByEmail(email);
//
//        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
//            throw  new Exception("Wrong otp ..");
//        }
        req.setEmail("seller_"+email);
        AuthResponse authResponse=authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }


}
