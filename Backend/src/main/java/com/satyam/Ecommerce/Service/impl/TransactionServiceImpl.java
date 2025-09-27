package com.satyam.Ecommerce.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Order;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Domain.Transaction;
import com.satyam.Ecommerce.Repo.SellerRepo;
import com.satyam.Ecommerce.Repo.TransactionRepo;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepo transactionRepo;
    private final SellerService sellerService;
    private final SellerRepo sellerRepo;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller=sellerRepo.findById(order.getSellerId()).get();
        Transaction transaction=new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySeller(Seller seller) {
        return transactionRepo.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }
    
}
