package com.satyam.Ecommerce.Response;

import com.razorpay.PaymentLink;

import lombok.Data;

@Data
public class PaymentLinkResponse {
    private String payment_link_url;
    private String payment_link_id;
}
