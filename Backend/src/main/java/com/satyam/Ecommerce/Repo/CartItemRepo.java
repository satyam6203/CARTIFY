package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Model.Cart;
import com.satyam.Ecommerce.Model.CartItem;
import com.satyam.Ecommerce.Model.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long>{
    
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
