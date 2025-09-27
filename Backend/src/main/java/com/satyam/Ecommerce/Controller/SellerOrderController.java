package com.satyam.Ecommerce.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Service.OrderService;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.constants.OrderStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {

    private final OrderService orderService;
    private final SellerService sellerService;

    public ResponseEntity<List<Order>> getAllOrdersHandler(
        @RequestHeader("Authorization")String jwt
    ) throws Exception{
        Seller seller=sellerService.getSellerProfile(jwt);
        List<Order> orders=orderService.sellersOrder(seller.getId());

        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOederHandler(
        @RequestHeader("Authorization")String jwt,
        @PathVariable Long OrderId,
        @PathVariable OrderStatus orderStatus
    ) throws Exception{
        Order order=orderService.updateOrderStatus(OrderId, orderStatus);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }
}
