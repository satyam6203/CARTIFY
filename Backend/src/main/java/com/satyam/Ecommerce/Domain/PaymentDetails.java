package com.satyam.Ecommerce.Domain;

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
    private String status;
}
