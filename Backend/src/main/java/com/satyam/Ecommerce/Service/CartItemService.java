package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Exceptions.CartItemException;
import com.satyam.Ecommerce.Exceptions.UserException;
import com.satyam.Ecommerce.Model.CartItem;

public interface CartItemService {
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
