package com.satyam.Ecommerce.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Wishlist;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Service.UserService;
import com.satyam.Ecommerce.Service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishListController {
    
    private final WishListService wishListService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishList(
        @RequestBody User user
    ){
        Wishlist wishlist=wishListService.createWishList(user);
        return ResponseEntity.ok(wishlist);
    }

    public ResponseEntity<Wishlist> getWishListByUserId(
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        
        return null;
    }
}
