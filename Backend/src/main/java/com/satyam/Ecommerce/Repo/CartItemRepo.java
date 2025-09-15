package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.CartItem;
import com.satyam.Ecommerce.Domain.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long>{
    
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
