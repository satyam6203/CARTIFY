package com.satyam.Ecommerce.Service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Address;
import com.satyam.Ecommerce.Domain.Cart;
import com.satyam.Ecommerce.Domain.CartItem;
import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.OrderItem;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.AddressRepo;
import com.satyam.Ecommerce.Repo.OrderItemRepo;
import com.satyam.Ecommerce.Repo.OrderRepo;
import com.satyam.Ecommerce.Service.OrderService;
import com.satyam.Ecommerce.constants.OrderStatus;
import com.satyam.Ecommerce.constants.PaymentStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final AddressRepo addressRepo;
    private final OrderItemRepo orderItemRepo;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if (!user.getAddress().contains(shippingAddress)) {
            user.getAddress().add(shippingAddress);
        }
        Address address = addressRepo.save(shippingAddress);

        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream().collect(
                Collectors.groupingBy(item -> item.getProduct().getSeller().getId())
        );

        Set<Order> orders = new HashSet<>();
        for (Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()) {
            Long sellerId = entry.getKey(); // ✅ changed (was SellerId with capital S)
            List<CartItem> items = entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(
                    CartItem::getSellingPrice
            ).sum();

            int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum(); // ✅ changed typo (tolalItems → totalItems)

            Order createOrder = new Order();
            createOrder.setUser(user);
            createOrder.setSellerId(sellerId);
            createOrder.setTotalMrpPrice(totalOrderPrice);
            createOrder.setTotalSellingPrice(totalOrderPrice);
            createOrder.setTotalItem(totalItems);
            createOrder.setShippingAddress(shippingAddress);
            createOrder.setOrderStatus(OrderStatus.PENDING);
            createOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order saveOrder = orderRepo.save(createOrder);
            orders.add(saveOrder);

            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem item : items) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(saveOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                saveOrder.getOrderItems().add(orderItem);

                OrderItem savedOrderItem = orderItemRepo.save(orderItem); // ✅ renamed variable (savOrderItems → savedOrderItem)
                orderItems.add(savedOrderItem);
            }
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long id) throws Exception {
        return orderRepo.findById(id).orElseThrow(() -> new Exception("Order not found.."));
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return orderRepo.findByUserId(userId); 
    }

    @Override
    public List<Order> sellersOrder(Long sellerId) { 
        return orderRepo.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepo.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception { // ✅ changed (cancleOrder → cancelOrder spelling fix)
        Order order = findOrderById(orderId);
        if (!user.getId().equals(order.getUser().getId())) {
            throw new Exception("you don't have access to this Order.."); // ✅ small grammar fix (Excess → access)
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        return orderRepo.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception { // ✅ changed (Id → id, variable names start lowercase)
        return orderItemRepo.findById(id).orElseThrow(() ->
                new Exception("Item not Exist.."));
    }
}
