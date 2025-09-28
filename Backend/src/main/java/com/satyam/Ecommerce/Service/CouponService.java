package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.Coupon;
import com.satyam.Ecommerce.Entity.User;

public interface CouponService {
    Cart applyCoupon(String code,double orderValue,User user)throws Exception;
    Cart removeCoupon(String code,User user)throws Exception;
    Coupon findCouponById(Long id)throws Exception;
    Coupon crateCoupon(Coupon coupon);
    List<Coupon> findAllCoupon();
    void deleteCoupon(Long id)throws Exception;
}
