package com.satyam.Ecommerce.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.CartItem;
import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Exceptions.ProductException;
import com.satyam.Ecommerce.Request.AddItemRequest;
import com.satyam.Ecommerce.Response.ApiResponse;
import com.satyam.Ecommerce.Service.CartItemService;
import com.satyam.Ecommerce.Service.CartService;
import com.satyam.Ecommerce.Service.ProductSerice;
import com.satyam.Ecommerce.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductSerice productSerice;

    @GetMapping()
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddItemRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception, ProductException {

        try {
            User user = userService.findUserByJwtToken(jwt);
            Product product = productSerice.findProductById(req.getProductId());
            CartItem item = cartService.addCartItem(
                user, 
                product, 
                req.getSize(), 
                req.getQuantity());

            ApiResponse apiResponse=new ApiResponse();
            apiResponse.setMessage("Data inserted SuccessFully.");

            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (ProductException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("item removed from Cart");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody CartItem cartItem) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        CartItem updateCartItem = null;
        if(cartItem.getQuantity() > 0) {
            updateCartItem = cartItemService.updateCartItem(
                    user.getId(),
                    cartItemId,
                    cartItem);
        }
        return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
    }
}
