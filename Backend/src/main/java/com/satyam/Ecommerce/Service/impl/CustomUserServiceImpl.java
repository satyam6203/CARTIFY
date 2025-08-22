package com.satyam.Ecommerce.Service.impl;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.SellerRepo;
import com.satyam.Ecommerce.Repo.UserRepo;
import com.satyam.Ecommerce.constants.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final SellerRepo sellerRepo;
    private static final String SELLER_PREFIX = "seller_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.startsWith(SELLER_PREFIX)){
            String ActualUserName = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepo.findByEmail(ActualUserName);
            if(seller != null){
                return buildUserDetails(
                        seller.getEmail(),
                        seller.getPassword(),
                        seller.getRole()
                );
            }
        }else{
            User user = userRepo.findByEmail(username);
            if(user != null){
                return buildUserDetails(user.getEmail(),user.getPassword(),user.getRole());
            }
        }
        throw  new UsernameNotFoundException("User or seller not found by this email"+username);
    }

    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if(role == null){
            role = USER_ROLE.ROLE_CUSTOMER;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return new org.springframework.security.core.userdetails.User(
                email,
                password,
                authorities
        );
    }
}
