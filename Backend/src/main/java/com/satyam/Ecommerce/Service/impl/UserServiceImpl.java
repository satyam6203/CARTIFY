package com.satyam.Ecommerce.Service.impl;

import com.satyam.Ecommerce.Config.JwtProvider;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.UserRepo;
import com.satyam.Ecommerce.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);
        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if(user ==  null){
            throw  new Exception("User not found with this email ->"+email);
        }
        return user;
    }
}
