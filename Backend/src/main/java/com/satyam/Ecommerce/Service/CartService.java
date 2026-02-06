package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Exceptions.ProductException;
import com.satyam.Ecommerce.Model.Cart;
import com.satyam.Ecommerce.Model.CartItem;
import com.satyam.Ecommerce.Model.Product;
import com.satyam.Ecommerce.Model.User;

public interface CartService {
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);
}
