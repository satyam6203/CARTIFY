package com.satyam.Ecommerce.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.satyam.Ecommerce.Domain.PaymentOrderStatus;
import com.satyam.Ecommerce.Domain.PayoutStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Payouts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    private Seller seller;

    private Long amount;

    private PayoutStatus status = PayoutStatus.PENDING;

    private LocalDateTime date=LocalDateTime.now();
}

