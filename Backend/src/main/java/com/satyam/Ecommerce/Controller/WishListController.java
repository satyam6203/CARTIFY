package com.satyam.Ecommerce.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Wishlist;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Service.ProductSerice;
import com.satyam.Ecommerce.Service.UserService;
import com.satyam.Ecommerce.Service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishListController {
    
    private final WishListService wishListService;
    private final UserService userService;
    private final ProductSerice productSerice;

    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishList(
        @RequestBody User user
    ){
        Wishlist wishlist=wishListService.createWishList(user);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping()
    public ResponseEntity<Wishlist> getWishListByUserId(
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Wishlist wishlist=wishListService.getWishListByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProduct(
        @PathVariable Long productId,
        @RequestHeader("Authrization") String jwt
        ) throws Exception {
        Product product=productSerice.findProductById(productId);
        User user=userService.findUserByJwtToken(jwt);
        Wishlist updateWishList=wishListService.addProductToWishlist(user, product);

        return ResponseEntity.ok(updateWishList);
    }
    
}
