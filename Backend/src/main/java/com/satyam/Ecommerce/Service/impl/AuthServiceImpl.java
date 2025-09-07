package com.satyam.Ecommerce.Service.impl;

import com.satyam.Ecommerce.Config.JwtProvider;
import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.VerificationCode;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.CartRepo;
import com.satyam.Ecommerce.Repo.SellerRepo;
import com.satyam.Ecommerce.Repo.UserRepo;
import com.satyam.Ecommerce.Repo.VerificationRepo;
import com.satyam.Ecommerce.Request.LoginRequest;
import com.satyam.Ecommerce.Response.AuthResponse;
import com.satyam.Ecommerce.Response.SignUpRequest;
import com.satyam.Ecommerce.Service.AuthService;
import com.satyam.Ecommerce.Service.EmailService;
import com.satyam.Ecommerce.constants.USER_ROLE;
import com.satyam.Ecommerce.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final VerificationRepo verificationRepo;
    private final PasswordEncoder passwordEncoder;
    private final CartRepo cartRepo;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final SellerRepo sellerRepo;

    @Override
    public String createUser(SignUpRequest req) throws Exception {

        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new RuntimeException("Password and Confirm Password do not match!");
        }

        VerificationCode verificationCode = verificationRepo.findByEmail(req.getEmail());
        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new Exception("Invalid OTP!");
        }

        User user = userRepo.findByEmail(req.getEmail());
        if (user == null) {
            User createUser = new User();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setMobile(req.getMobile());
            createUser.setRole(USER_ROLE.ROLE_CUSTOMER);

            createUser.setPassword(passwordEncoder.encode(req.getPassword()));

            user = userRepo.save(createUser);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_USER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public void sendLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";
        // String SELLER_PREFIX = "seller_";
        if (email.startsWith(SIGNING_PREFIX)) {

            email = email.substring(SIGNING_PREFIX.length());

            if (role.equals(USER_ROLE.ROLE_SELLER)) {
                Seller seller = sellerRepo.findByEmail(email);
                if (seller == null) {
                    throw new Exception("Seller not found");
                }
            } else {
                User user = userRepo.findByEmail(email);
                if (user == null) {
                    throw new Exception("User Not exist with the provided email..");
                }

            }

        }
        VerificationCode isExist = verificationRepo.findByEmail(email);
        if (isExist != null) {
            verificationRepo.delete(isExist);
        }
        String otp = OtpUtils.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationRepo.save(verificationCode);

        String subject = "PU Mart Login / SignUp OTP";

        String text = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <title>PU Mart OTP</title>" +
                "</head>" +
                "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'>" +
                "    <div style='max-width: 500px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>"
                +
                "        <h2 style='color: #333333; text-align: center;'>PU Mart Verification</h2>" +
                "        <p style='font-size: 16px; color: #555555; text-align: center;'>Your Login / SignUp OTP is:</p>"
                +
                "        <div style='text-align: center; margin: 20px 0;'>" +
                "            <span style='display: inline-block; padding: 12px 25px; font-size: 24px; font-weight: bold; color: #ffffff; background-color: #4CAF50; border-radius: 8px;'>"
                + otp + "</span>" +
                "        </div>" +
                "        <p style='font-size: 14px; color: #777777; text-align: center;'>This OTP will expire in <b>10 minutes</b>. Please do not share it with anyone.</p>"
                +
                "        <hr style='margin: 20px 0;'>" +
                "        <p style='font-size: 12px; color: #aaaaaa; text-align: center;'>© 2025 PU Mart. All rights reserved.</p>"
                +
                "    </div>" +
                "</body>" +
                "</html>";

        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }

    @Override
    public AuthResponse signing(LoginRequest request) {

        String userName = request.getEmail();
        String otp = request.getOtp();

        Authentication authentication = authenticate(userName, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success..");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(roleName));

        User user = userRepo.findByEmail(userName);

        return authResponse;
    }

    private Authentication authenticate(String userName, String otp) {

        UserDetails userDetails = customUserService.loadUserByUsername(userName);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid UserName or Password");
        }

        VerificationCode verificationCode = verificationRepo.findByEmail(userName);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BadCredentialsException("Wrong Otp..");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }
}
