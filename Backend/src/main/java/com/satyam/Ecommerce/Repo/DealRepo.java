package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Deal;

@Repository
public interface DealRepo extends JpaRepository<Deal,Long>{
    
}
