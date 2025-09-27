package com.satyam.Ecommerce.Service.impl;

import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Order;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.satyam.Ecommerce.Domain.PaymentOrder;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.OrderRepo;
import com.satyam.Ecommerce.Repo.PaymentOrderRepo;
import com.satyam.Ecommerce.Service.PaymentService;
import com.satyam.Ecommerce.constants.PaymentOrderStatus;
import com.satyam.Ecommerce.constants.PaymentStatus;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    
    private final PaymentOrderRepo paymentOrderRepo;
    private final OrderRepo orderRepo;

    private String apikey="apikey";
    private String apiSecret="apiSecret";
    private String stripSecretKey="stripSecretKey";

    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        Long amount=orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrder paymentOrder=new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setUser(user);
        paymentOrder.setOrders(orders);
        return paymentOrderRepo.save(paymentOrder);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {

        return paymentOrderRepo.findById(orderId).orElseThrow(()->
            new Exception("Payment order not found.")
        );
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception {
        PaymentOrder paymentOrder=paymentOrderRepo.findByPaymentLink(orderId);
        if(paymentOrder == null){
            throw new Exception("payment order not found with payment Link Id.");
        }
        return paymentOrder;
    }

    @Override
    public Boolean ProceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){
            RazorpayClient razorPay=new RazorpayClient(apikey,apiSecret);

            Payment payment=razorPay.payments.fetch(paymentLinkId);
            String status=payment.get("status");
            if(status.equals("captured")){
                Set<Order> orders=paymentOrder.getOrders();
                for(Order order:orders){
                    order.setPaymentStatus(PaymentStatus.COMPLETED);
                    orderRepo.save(order);
                }
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepo.save(paymentOrder);
                return true;
            }
            paymentOrder.setStatus(PaymentOrderStatus.FAILED);
            paymentOrderRepo.save(paymentOrder);
            return false;
        }
        return false;
    }

    @Override
    public PaymentLink createRazorPaymentLink(User user, Long amount, Long orderId) throws RazorpayException {
        amount=amount*100;
        try{
            RazorpayClient razorPay=new RazorpayClient(apikey,apiSecret);

            JSONObject paymentLinkRequest=new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");

            JSONObject customer=new JSONObject();
            customer.put("name", user.getFullName());
            customer.put("email", user.getEmail());
            paymentLinkRequest.put("customer", customer);

            JSONObject notify=new JSONObject();
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", 
                    "http://localhost:3000/payment-success"+orderId);

            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink=razorPay.paymentLink.create(paymentLinkRequest);
            String paymentLinkUrl=paymentLink.get("short_url");
            String paymentLinkId=paymentLink.get("id");

            return paymentLink;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw new RazorpayException(e.getMessage());
        }
    }

    @Override
    public String craeteStripePaymentLink(User user, Long amount, Long orderId) {
    Stripe.apiKey = stripSecretKey;

    try {
        SessionCreateParams params = SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:3000/payment-success" + orderId)
            .setCancelUrl("http://localhost:3000/payment-cancel" + orderId)
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setQuantity(1L)
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("USD")
                            .setUnitAmount(amount * 100)
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("PU MART")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
            .build();

        Session session = Session.create(params);
        return session.getUrl();

    } catch (Exception e) {
        throw new RuntimeException("Stripe payment failed: " + e.getMessage(), e);
    }
}

    
}
