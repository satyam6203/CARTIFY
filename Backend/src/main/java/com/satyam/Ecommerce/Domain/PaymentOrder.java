package com.satyam.Ecommerce.Domain;

import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.constants.PaymentMethod;
import com.satyam.Ecommerce.constants.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus status=PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private String PaymentLink;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Order> orders=new HashSet<>();
}
