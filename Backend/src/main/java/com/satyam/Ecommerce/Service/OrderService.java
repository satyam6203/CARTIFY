package com.satyam.Ecommerce.Service;

import java.util.List;
import java.util.Set;

import com.satyam.Ecommerce.Domain.Address;
import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.OrderItem;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.constants.OrderStatus;

public interface OrderService {
    Set<Order> createOrder(User user,Address shippingAddress,Cart cart);
    Order findOrderById(Long id)throws Exception;
    List<Order> usersOrderHistory(Long userId);
    List<Order> sellersOrder(Long SellerId);
    Order updateOrderStatus(Long orderId,OrderStatus orderStatus)throws Exception;
    Order cancelOrder(Long orderId,User user)throws Exception;
    OrderItem getOrderItemById(Long Id)throws Exception;
}
