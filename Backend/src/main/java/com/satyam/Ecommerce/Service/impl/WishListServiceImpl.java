package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Wishlist;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.WishListRepo;
import com.satyam.Ecommerce.Service.WishListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListServiceImpl implements WishListService{

    private final WishListRepo wishListRepo;

    @Override
    public Wishlist createWishList(User user) {
        Wishlist wishlist=new Wishlist();
        wishlist.setUser(user);
        return wishListRepo.save(wishlist);
    }

    @Override
    public Wishlist getWishListByUserId(User user) {
        Wishlist wishlist= wishListRepo.findByUserId(user.getId());
        if(wishlist == null){
            wishlist=createWishList(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist=getWishListByUserId(user);
         
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);
        return wishListRepo.save(wishlist);
    } 
}
