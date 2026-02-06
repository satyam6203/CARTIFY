package com.satyam.Ecommerce.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long id);
}
