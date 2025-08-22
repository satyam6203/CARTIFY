package com.satyam.Ecommerce.Controller;

import com.satyam.Ecommerce.Domain.VerificationCode;
import com.satyam.Ecommerce.Request.LoginOtpRequest;
import com.satyam.Ecommerce.Request.LoginRequest;
import com.satyam.Ecommerce.Response.ApiResponse;
import com.satyam.Ecommerce.Response.AuthResponse;
import com.satyam.Ecommerce.Service.AuthService;
import com.satyam.Ecommerce.constants.USER_ROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.UserRepo;
import com.satyam.Ecommerce.Response.SignUpRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest req) throws Exception {
        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register successfully");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> SendOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sendLoginOtp(req.getEmail(),req.getRole());
        ApiResponse res = new ApiResponse();
        res.setMessage("otp sent successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> LoginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.signing(req);
        AuthResponse res = new AuthResponse();
        res.setMessage("Login Successfully..");
        return ResponseEntity.ok(res);
    }
}
