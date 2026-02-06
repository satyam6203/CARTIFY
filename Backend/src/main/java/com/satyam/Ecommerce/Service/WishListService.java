package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Exceptions.WishlistNotFoundException;
import com.satyam.Ecommerce.Model.Product;
import com.satyam.Ecommerce.Model.User;
import com.satyam.Ecommerce.Model.Wishlist;

public interface WishListService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user);

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException;

}
