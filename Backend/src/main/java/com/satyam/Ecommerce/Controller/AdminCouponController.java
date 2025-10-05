package com.satyam.Ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.Coupon;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Service.CartService;
import com.satyam.Ecommerce.Service.CouponService;
import com.satyam.Ecommerce.Service.UserService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class AdminCouponController {

    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(
        @RequestParam String apply,
        @RequestParam String code,
        @RequestParam double orderValue,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Cart cart;
        if(apply.equals("true")){
            cart=couponService.applyCoupon(code, orderValue, user);
        }
        else{
            cart=couponService.removeCoupon(code, user);
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
        Coupon createCoupon=couponService.crateCoupon(coupon);
        return ResponseEntity.ok(createCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) throws Exception{
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("coupon deleted successFully");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> coupons=couponService.findAllCoupon();
        return ResponseEntity.ok(coupons);
    }
}
