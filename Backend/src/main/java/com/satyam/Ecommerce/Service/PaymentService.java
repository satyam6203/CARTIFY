package com.satyam.Ecommerce.Service;

import java.util.Set;


import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.satyam.Ecommerce.Model.Order;
import com.satyam.Ecommerce.Model.PaymentOrder;
import com.satyam.Ecommerce.Model.User;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(User user,
                             Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;

    Boolean ProceedPaymentOrder (PaymentOrder paymentOrder,
                                 String paymentId, String paymentLinkId) throws RazorpayException;

    PaymentLink createRazorpayPaymentLink(User user,
                                          Long Amount,
                                          Long orderId) throws RazorpayException;

    String createStripePaymentLink(User user, Long Amount,
                                            Long orderId) throws StripeException;
}
