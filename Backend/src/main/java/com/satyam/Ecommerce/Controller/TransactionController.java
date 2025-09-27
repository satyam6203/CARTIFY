package com.satyam.Ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.Transaction;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        Seller seller=sellerService.getSellerProfile(jwt);
        List<Transaction> transactions=transactionService.getTransactionsBySeller(seller);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions=transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
