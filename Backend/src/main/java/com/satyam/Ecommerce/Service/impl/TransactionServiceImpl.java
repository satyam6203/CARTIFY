package com.satyam.Ecommerce.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Model.Order;
import com.satyam.Ecommerce.Model.Seller;
import com.satyam.Ecommerce.Model.Transaction;
import com.satyam.Ecommerce.Repo.SellerRepo;
import com.satyam.Ecommerce.Repo.TransactionRepo;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.Service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepository;
    private final SellerRepo sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller = sellerRepository.findById(order.getSellerId()).get();
        Transaction transaction = new Transaction();
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        transaction.setSeller(seller);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionBySeller(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
