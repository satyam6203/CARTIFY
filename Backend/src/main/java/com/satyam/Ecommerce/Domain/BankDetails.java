package com.satyam.Ecommerce.Domain;

import lombok.Data;

@Data
public class BankDetails {
    private String accountNumber;
    private String accountHolder;
    private String ifscCode;
}
