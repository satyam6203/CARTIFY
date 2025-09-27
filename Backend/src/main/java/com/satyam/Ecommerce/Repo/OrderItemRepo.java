package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long>{
    
}
