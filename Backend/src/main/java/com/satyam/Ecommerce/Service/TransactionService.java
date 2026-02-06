package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Model.Order;
import com.satyam.Ecommerce.Model.Seller;
import com.satyam.Ecommerce.Model.Transaction;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}
