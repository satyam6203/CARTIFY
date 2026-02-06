package com.satyam.Ecommerce.Response;

import com.satyam.Ecommerce.Model.Cart;
import com.satyam.Ecommerce.Model.Product;
import com.satyam.Ecommerce.dto.OrderHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse {
    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;
}
