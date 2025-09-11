package com.satyam.Ecommerce.Controller;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.VerificationCode;
import com.satyam.Ecommerce.Exceptions.SellerException;
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

        String subject = "PU MART Email Verification Code";

        String verificationLink = "https://localhost:3000/verify-seller/" + otp;

        String text = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <title>PU MART Email Verification</title>" +
                "</head>" +
                "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'>" +
                "    <div style='max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);'>"
                +
                "        <h2 style='color: #333333; text-align: center;'>Welcome to PU MART!</h2>" +
                "        <p style='font-size: 16px; color: #555555; text-align: center;'>Please verify your account by clicking the button below:</p>"
                +
                "        <div style='text-align: center; margin: 25px 0;'>" +
                "            <a href='" + verificationLink + "' " +
                "               style='display: inline-block; padding: 12px 25px; font-size: 18px; font-weight: bold; color: #ffffff; background-color: #4CAF50; text-decoration: none; border-radius: 8px;'>"
                +
                "               Verify My Account" +
                "            </a>" +
                "        </div>" +
                "        <p style='font-size: 14px; color: #777777; text-align: center;'>Or you can copy and paste the following link into your browser:</p>"
                +
                "        <p style='font-size: 14px; color: #2563EB; text-align: center; word-break: break-all;'>"
                + verificationLink + "</p>" +
                "        <p style='font-size: 14px; color: #777777; text-align: center;'>This link will expire in <b>10 minutes</b>. Please do not share it with anyone.</p>"
                +
                "        <hr style='margin: 20px 0;'>" +
                "        <p style='font-size: 12px; color: #aaaaaa; text-align: center;'>© 2025 PU MART. All rights reserved.</p>"
                +
                "    </div>" +
                "</body>" +
                "</html>";

        emailService.sendVerificationOtpEmail(
                savedSeller.getEmail(),
                verificationCode.getOtp(),
                subject,
                text + verificationLink);

        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerByID(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/getAll-seller")
    public ResponseEntity<List<Seller>> getAllSeller(@RequestParam(required = false) AccountStatus status) {
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping("/update-seller")
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
