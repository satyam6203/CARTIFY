package com.satyam.Ecommerce.Controller;

import com.satyam.Ecommerce.Config.JwtProvider;
import com.satyam.Ecommerce.Domain.AccountStatus;
import com.satyam.Ecommerce.Domain.USER_ROLE;
import com.satyam.Ecommerce.Exceptions.SellerException;
import com.satyam.Ecommerce.Model.Seller;
import com.satyam.Ecommerce.Model.SellerReports;
import com.satyam.Ecommerce.Model.VerificationCode;
import com.satyam.Ecommerce.Repo.VerificationRepo;
import com.satyam.Ecommerce.Response.ApiResponse;
import com.satyam.Ecommerce.Response.AuthResponse;
import com.satyam.Ecommerce.Service.EmailService;
import com.satyam.Ecommerce.Service.SellerReportService;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.VerificationService;
import com.satyam.Ecommerce.Service.impl.CustomUserServiceImpl;
import com.satyam.Ecommerce.utils.OtpUtils;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final EmailService emailService;
    private final VerificationRepo verificationCodeRepository;
    private final VerificationService verificationService;
    private final JwtProvider jwtProvider;
    private final CustomUserServiceImpl customeUserServiceImplementation;


    @PostMapping("/sent/login-top")
    public ResponseEntity<ApiResponse> sentLoginOtp(@RequestBody VerificationCode req) throws Exception {
        Seller seller = sellerService.getSellerByEmail(req.getEmail());

        String otp = OtpUtils.generateOTP();
        VerificationCode verificationCode = verificationService.createVerificationCode(otp, req.getEmail());

        String subject = "Catify Login OTP";

        String text = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <title>Catify OTP</title>" +
                "</head>" +
                "<body style='font-family: Arial, sans-serif; background-color: #e6f3f1; margin: 0; padding: 0;'>" +
                "    <div style='max-width: 480px; margin: 25px auto; background-color: #ffffff; padding: 22px; border-radius: 10px; box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);'>" +
                "        <h2 style='text-align: center; color: #00927c; margin-bottom: 10px;'>Catify Login OTP</h2>" +
                "        <p style='text-align: center; font-size: 16px; color: #444;'>Your login OTP is:</p>" +
                "        <div style='text-align: center; margin: 20px 0;'>" +
                "            <span style='display: inline-block; background-color: #00927c; color: #ffffff; padding: 12px 30px; font-size: 24px; font-weight: bold; border-radius: 8px;'>"
                + otp + "</span>" +
                "        </div>" +
                "        <p style='text-align: center; color: #666; font-size: 14px;'>This OTP will expire in <b>10 minutes</b>. Please do not share it with anyone.</p>" +
                "    </div>" +
                "</body>" +
                "</html>";

        emailService.sendVerificationOtpEmail(req.getEmail(), verificationCode.getOtp(), subject, text);

        ApiResponse res = new ApiResponse();
        res.setMessage("otp sent");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/verify/login-top")
    public ResponseEntity<AuthResponse> verifyLoginOtp(@RequestBody VerificationCode req) throws MessagingException, SellerException {
//        Seller savedSeller = sellerService.createSeller(seller);


        String otp = req.getOtp();
        String email = req.getEmail();
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new SellerException("wrong otp...");
        }

        Authentication authentication = authenticate(req.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();


        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username) {
        UserDetails userDetails = customeUserServiceImplementation.loadUserByUsername("seller_" + username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {


        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new SellerException("wrong otp...");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtils.generateOTP();
        VerificationCode verificationCode = verificationService.createVerificationCode(otp, seller.getEmail());

        String subject = "Catify Email Verification";

        String frontend_url = "http://localhost:3000/verify-seller/" + otp;

        String text = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <title>Catify Email Verification</title>" +
                "</head>" +
                "<body style='font-family: Arial, sans-serif; background-color: #e6f3f1; margin: 0; padding: 0;'>" +
                "    <div style='max-width: 520px; margin: 25px auto; background-color: #ffffff; padding: 24px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);'>" +
                "        <h2 style='color: #00927c; text-align: center;'>Welcome to Catify!</h2>" +
                "        <p style='font-size: 16px; color: #444444; text-align: center;'>Click the button below to verify your account:</p>" +
                "        <div style='text-align: center; margin: 26px 0;'>" +
                "            <a href='" + frontend_url + "' " +
                "               style='display: inline-block; padding: 14px 28px; font-size: 18px; font-weight: bold; color: #ffffff; background-color: #00927c; text-decoration: none; border-radius: 10px;'>Verify Email</a>" +
                "        </div>" +
                "        <p style='font-size: 14px; color: #666; text-align: center;'>If the button does not work, copy and paste this link into your browser:</p>" +
                "        <p style='font-size: 14px; color: #00927c; text-align: center; word-break: break-all;'>" + frontend_url + "</p>" +
                "        <p style='font-size: 14px; color: #666; text-align: center;'>This verification link is valid for <b>10 minutes</b>. Please keep your account secure.</p>" +
                "        <hr style='margin: 25px 0; border:none; height:1px; background-color:#dddddd;'>" +
                "        <p style='font-size: 12px; color: #999; text-align: center;'>© 2025 Catify. All rights reserved.</p>" +
                "    </div>" +
                "</body>" +
                "</html>";

        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, text + frontend_url);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(
            @RequestHeader("Authorization") String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReports> getSellerReport(
            @RequestHeader("Authorization") String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);
        SellerReports report = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(
            @RequestParam(required = false) AccountStatus status) {
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(
            @RequestHeader("Authorization") String jwt, @RequestBody Seller seller) throws Exception {

        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updatedSeller);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {

        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();

    }
}
