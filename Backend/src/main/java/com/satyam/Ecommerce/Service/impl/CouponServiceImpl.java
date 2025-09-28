package com.satyam.Ecommerce.Service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.Coupon;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.CartRepo;
import com.satyam.Ecommerce.Repo.CouponRepo;
import com.satyam.Ecommerce.Repo.UserRepo;
import com.satyam.Ecommerce.Service.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponRepo couponRepo;
    private final CartRepo cartRepo;
    private final UserRepo userRepo;


    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
        Coupon coupon=couponRepo.findByCode(code);
        Cart cart=cartRepo.findByUserId(user.getId());
        if(coupon == null){
            throw new Exception("Coupon not valid");
        }
        if(user.getUsedCoupon().contains(coupon)){
            throw new Exception("coupon already Userd");
        }
        if(orderValue < coupon.getMinimumOrderValue()){
            throw new Exception("coupon order less than minmum order value "+coupon.getMinimumOrderValue());
        }
        if(coupon.isActive() && 
            LocalDate.now().isAfter(coupon.getValidityEndDate())
            && LocalDate.now().isBefore(coupon.getValidityEndDate())
        ){
            user.getUsedCoupon().add(coupon);
            userRepo.save(user);

            double discountedPrice=(cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountedPrice);
            cart.setCouponCode(code);
            cartRepo.save(cart);
            return cart;
        }
        throw new Exception("coupon not valied");
    }

    @Override
    public Cart removeCoupon(String code, User user) throws Exception {
        Coupon coupon=couponRepo.findByCode(code);
        if(code == null){
            throw new Exception("coupon not found..");
        }
        Cart cart=cartRepo.findByUserId(user.getId());
        double discountedPrice=(cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountedPrice);
        cart.setCouponCode(null);
        return cartRepo.save(cart);
    }

    @Override
    public Coupon findCouponById(Long id) throws Exception {
        return couponRepo.findById(id).orElseThrow(()->
            new Exception("coupon not found"));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon crateCoupon(Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupon() {
        return couponRepo.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCoupon(Long id) throws Exception {
        findCouponById(id);
        couponRepo.deleteById(id);
    }
    
}
