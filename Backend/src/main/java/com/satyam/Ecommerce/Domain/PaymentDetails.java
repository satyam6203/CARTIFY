package com.satyam.Ecommerce.Domain;

import com.satyam.Ecommerce.constants.PaymentStatus;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PaymentDetails {
    private String paymentId;
    private String razorPayPaymentLinkId;
    private String  razorPayPaymentLinkReferenceId;
    private String razorPayPaymentLinkStatus;
    private String razorPayPaymentIdZWSP;
    private PaymentStatus status;
}
