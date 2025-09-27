package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.Transaction;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySeller(Seller seller);
    List<Transaction> getAllTransactions();
}
