package com.skypay.skypay.service;

import com.skypay.skypay.model.Account;
import com.skypay.skypay.model.Transaction;

public interface TransactionInt {
    Transaction deposit(int amount,Account account);
    Transaction withdraw(int amount,Account account);
    void printStatement(Account account);
}
