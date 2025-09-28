package com.satyam.Ecommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
    List<Review> findByProductId(Long productId);
}
