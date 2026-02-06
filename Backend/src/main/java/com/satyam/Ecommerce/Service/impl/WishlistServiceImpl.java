package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Exceptions.WishlistNotFoundException;
import com.satyam.Ecommerce.Model.Product;
import com.satyam.Ecommerce.Model.User;
import com.satyam.Ecommerce.Model.Wishlist;
import com.satyam.Ecommerce.Repo.WishListRepo;
import com.satyam.Ecommerce.Service.WishListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishListService {

    private final WishListRepo wishlistRepository;


    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        Wishlist wishlist = wishlistRepository.findByUserId(user.getId());
        if (wishlist == null) {
            wishlist = this.createWishlist(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException {
        Wishlist wishlist = this.getWishlistByUserId(user);
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);

        return wishlistRepository.save(wishlist);
    }


}

