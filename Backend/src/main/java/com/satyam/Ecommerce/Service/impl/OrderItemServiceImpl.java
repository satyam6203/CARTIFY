package com.satyam.Ecommerce.Service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Exceptions.OrderException;
import com.satyam.Ecommerce.Model.OrderItem;
import com.satyam.Ecommerce.Repo.OrderItemRepo;
import com.satyam.Ecommerce.Service.OrderItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {


    private final OrderItemRepo orderItemRepository;


    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {

        System.out.println("------- "+id);
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if(orderItem.isPresent()){
            return orderItem.get();
        }
        throw new OrderException("Order item not found");
    }
}
