package com.satyam.Ecommerce.Domain;

import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.constants.OrderStatus;
import com.satyam.Ecommerce.constants.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private Long sellerId;

    private String orderId;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order",orphanRemoval = true)
    private List<OrderItem> orderItems=new ArrayList<>();

    @ManyToOne
    private Address shippingAddress;

    private PaymentDetails paymentDetails=new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;
    
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    private int totalItem;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    private LocalDateTime orderDate=LocalDateTime.now();

    private LocalDateTime deliverDate=orderDate.plusDays(7);
}
