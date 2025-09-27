package com.satyam.Ecommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long>{
    
    List<Transaction> findBySellerId(Long sellerId);
}
