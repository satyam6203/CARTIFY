package com.satyam.Ecommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.Ecommerce.Domain.PaymentOrderStatus;
import com.satyam.Ecommerce.Model.Payouts;

public interface PayoutsRepository extends JpaRepository<Payouts,Long> {

    List<Payouts> findPayoutsBySellerId(Long sellerId);
    List<Payouts> findAllByStatus(PaymentOrderStatus status);
}
