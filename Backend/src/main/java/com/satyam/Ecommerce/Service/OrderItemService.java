package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Model.OrderItem;

public interface OrderItemService {
	OrderItem getOrderItemById(Long id) throws Exception;
}
