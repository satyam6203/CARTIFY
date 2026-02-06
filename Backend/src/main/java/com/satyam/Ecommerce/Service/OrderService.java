package com.satyam.Ecommerce.Service;

import java.util.List;
import java.util.Set;

import com.satyam.Ecommerce.Domain.OrderStatus;
import com.satyam.Ecommerce.Exceptions.OrderException;
import com.satyam.Ecommerce.Model.Address;
import com.satyam.Ecommerce.Model.Cart;
import com.satyam.Ecommerce.Model.Order;
import com.satyam.Ecommerce.Model.User;


public interface OrderService {
	
	public Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public List<Order>getShopsOrders(Long sellerId);

	public Order updateOrderStatus(Long orderId,
								   OrderStatus orderStatus)
			throws OrderException;
	
	public void deleteOrder(Long orderId) throws OrderException;

	Order cancelOrder(Long orderId,User user) throws OrderException;
	
}
