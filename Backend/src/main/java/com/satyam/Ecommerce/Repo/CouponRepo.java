package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long>{

    Coupon findByCode(String code);
    
}
