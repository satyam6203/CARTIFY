package com.satyam.Ecommerce.Service;

import java.util.Set;


import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.PaymentOrder;
import com.satyam.Ecommerce.Entity.User;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId)throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId)throws Exception;
    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,
                            String paymentId,
                            String paymentLinkId)throws RazorpayException;
    PaymentLink createRazorPaymentLink(User user,Long amount,Long orderId)throws RazorpayException;
    String craeteStripePaymentLink(User user,Long amount,Long orderId);
}
