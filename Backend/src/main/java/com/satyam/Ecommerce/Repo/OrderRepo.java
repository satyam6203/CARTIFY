package com.satyam.Ecommerce.Repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>{
    List<com.satyam.Ecommerce.Model.Order> findByUserId(Long userId);
    List<com.satyam.Ecommerce.Model.Order> findBySellerIdOrderByOrderDateDesc(Long sellerId);
    List<Order> findBySellerIdAndOrderDateBetween(Long sellerId,LocalDateTime startDate, LocalDateTime endDate);
}
