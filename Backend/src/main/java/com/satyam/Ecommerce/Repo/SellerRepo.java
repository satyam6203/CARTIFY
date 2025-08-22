package com.satyam.Ecommerce.Repo;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.constants.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
