package com.satyam.Ecommerce.Controller;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.VerificationCode;
import com.satyam.Ecommerce.Repo.VerificationRepo;
import com.satyam.Ecommerce.Request.LoginRequest;
import com.satyam.Ecommerce.Response.AuthResponse;
import com.satyam.Ecommerce.Service.AuthService;
import com.satyam.Ecommerce.Service.EmailService;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.constants.AccountStatus;
import com.satyam.Ecommerce.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final VerificationRepo verificationRepo;
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception {

        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_" + email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(
            @PathVariable String otp) throws Exception {

        VerificationCode verificationCode = verificationRepo.findByOtp(otp);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong otp..");
        }
        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {

        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtils.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(savedSeller.getEmail());
        verificationRepo.save(verificationCode);

        // Prepare email content
        String subject = "PU MART Email Verification Code";
        String text = "Welcome to PU MART! Please verify your account using the following link:\n\n";
        String verificationLink = "https://localhost:3000/verify-seller/" + otp;

        // Send email
        emailService.sendVerificationOtpEmail(
                savedSeller.getEmail(),
                verificationCode.getOtp(),
                subject,
                text + verificationLink);
                
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @PostMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception {
        Seller seller = sellerService.getSellerByID(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/getAll-seller")
    public ResponseEntity<List<Seller>> getAllSeller(@RequestParam(required = false) AccountStatus status) {
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping("/update/seller")
    public ResponseEntity<Seller> updateSeller(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Seller seller) throws Exception {

        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updateSeller = sellerService.updateSeller(profile.getId(), seller);

        return ResponseEntity.ok(updateSeller);
    }

    @DeleteMapping("/delete/seller")
    public ResponseEntity<Void> deleteSeller(@PathVariable long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
