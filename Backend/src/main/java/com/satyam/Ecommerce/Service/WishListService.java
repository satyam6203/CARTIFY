package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Wishlist;
import com.satyam.Ecommerce.Entity.User;

public interface WishListService {
    Wishlist createWishList(User user);
    Wishlist getWishListByUserId(User user);
    Wishlist addProductToWishlist(User user,Product product);
}
