package com.satyam.Ecommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>{
    List<Order> findByUserId(Long userId);
    List<Order> findBySellerId(Long SellerId);
}
