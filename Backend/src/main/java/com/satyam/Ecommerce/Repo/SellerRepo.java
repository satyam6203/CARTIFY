package com.satyam.Ecommerce.Repo;

import com.satyam.Ecommerce.Domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
}
