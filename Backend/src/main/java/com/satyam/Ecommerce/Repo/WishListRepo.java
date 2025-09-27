package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Wishlist;

@Repository
public interface WishListRepo extends JpaRepository<Wishlist,Long>{
    Wishlist findByUserId(Long userId);
}
