package com.satyam.Ecommerce.Repo;

import com.satyam.Ecommerce.Domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {

}
