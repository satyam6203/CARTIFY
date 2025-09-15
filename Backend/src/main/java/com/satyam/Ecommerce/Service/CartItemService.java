package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Domain.CartItem;

public interface CartItemService {
    CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws Exception;

    void removeCartItem(Long userId, Long cartItemId)throws Exception;

    CartItem finCartItemById(Long id)throws Exception;
}
