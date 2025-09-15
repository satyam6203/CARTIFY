package com.satyam.Ecommerce.Service.impl;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.CartItem;
import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.CartItemRepo;
import com.satyam.Ecommerce.Repo.CartRepo;
import com.satyam.Ecommerce.Service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepo.findByCartAndProductAndSize(cart, product, size);
        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product); 
            cartItem.setQuantity(quantity);
            cartItem.setSize(size);
            cartItem.setUserId(user.getId());
            cartItem.setMrpPrice(quantity * product.getMrpPrice());
            cartItem.setSellingPrice(quantity * product.getSellingPrice());
            cartItem.setCart(cart);

            cart.getCartItems().add(cartItem);
            return cartItemRepo.save(cartItem);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepo.findByUserId(user.getId());

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalItem(totalItem);
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));

        return cartRepo.save(cart);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {

        if (mrpPrice <= 0) {
            return 0;
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercent = (discount / mrpPrice) * 100;
        return (int) discountPercent;
    }
}
