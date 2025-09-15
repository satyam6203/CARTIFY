package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.CartItem;
import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Entity.User;

public interface CartService {
    public CartItem addCartItem(
        User user,
        Product product,
        String size,
        int quantity
    );
    public Cart findUserCart(User user);
}
