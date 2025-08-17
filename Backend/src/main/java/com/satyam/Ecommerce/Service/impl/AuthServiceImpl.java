package com.satyam.Ecommerce.Service.impl;

import com.satyam.Ecommerce.Config.JwtProvider;
import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.CartRepo;
import com.satyam.Ecommerce.Repo.UserRepo;
import com.satyam.Ecommerce.Repo.VerificationRepo;
import com.satyam.Ecommerce.Response.SignUpRequest;
import com.satyam.Ecommerce.Service.AuthService;
import com.satyam.Ecommerce.constants.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    VerificationRepo verificationRepo;
    private final PasswordEncoder passwordEncoder;
    private final CartRepo cartRepo;
    private final JwtProvider jwtProvider;

    @Override
    public String createUser(SignUpRequest req) {
        

        User user=userRepo.findByEmail(req.getEmail());
        if(user==null){
            User createUser=new User();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setMobile("2464654655");
            createUser.setRole(USER_ROLE.ROLE_USER);
            createUser.setPassword(passwordEncoder.encode(req.getOtp()));
            user=userRepo.save(createUser);

            Cart cart=new Cart();
            cart.setUser(user);
            cartRepo.save(cart);
        }

        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
        Authentication authentication=new UsernamePasswordAuthenticationToken(req.getEmail(),null,authorities);
        SecurityContextHolder .getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }
}
