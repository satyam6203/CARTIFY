package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.CartItem;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.CartItemRepo;
import com.satyam.Ecommerce.Service.CartItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepo cartItemRepo;

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = finCartItemById(id);

        User user = item.getCart().getUser();

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());

            return cartItemRepo.save(item);
        }
        throw new Exception("You can't Update this cartItem");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item = finCartItemById(cartItemId);

        User user = item.getCart().getUser();

        if (user.getId().equals(userId)) {
            cartItemRepo.delete(item);
        } else
            throw new Exception("You can't delete this items");
    }

    @Override
    public CartItem finCartItemById(Long id) throws Exception {
        return cartItemRepo.findById(id).orElseThrow(() -> new Exception("Item not found by this id " + id));
    }

}
