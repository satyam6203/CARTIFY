package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Model.Cart;
import com.satyam.Ecommerce.Model.Coupon;
import com.satyam.Ecommerce.Model.User;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon createCoupon(Coupon coupon);
    void deleteCoupon(Long couponId);
    List<Coupon> getAllCoupons();
    
    Coupon getCouponById(Long couponId);
}
