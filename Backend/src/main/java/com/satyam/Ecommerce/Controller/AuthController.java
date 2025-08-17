package com.satyam.Ecommerce.Controller;

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
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest req){
        String jwt=authService.createUser(req);
        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register successfully");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(res);
    }
}
