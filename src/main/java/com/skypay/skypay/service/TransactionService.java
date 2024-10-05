package com.skypay.skypay.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skypay.skypay.enumeration.TransactionType;
import com.skypay.skypay.model.Account;
import com.skypay.skypay.model.Transaction;
import com.skypay.skypay.repository.TransactionRepo;

@Service
public class TransactionService implements TransactionInt{

    @Autowired
    private TransactionRepo transactionrepo;
   


    @Override
    public Transaction deposit(int amount,Account account) {
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(date); 
        transaction.setTransactionType(TransactionType.DEPOSIT); 
        transaction.setAccount(account);
        transactionrepo.save(transaction);
        return transaction;
    }

    @Override
    public Transaction withdraw(int amount,Account account) {
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(date); 
        transaction.setTransactionType(TransactionType.WITHDRAWAL); 
        transaction.setAccount(account);
        transactionrepo.save(transaction);
        return transaction;
        
    }

    @Override
    public void printStatement(Account account) {
        List<Map<String,Object>> TresactionListTemp=this.getTransactionByAccount(account);
        System.out.printf("Date                | Amount       |  Balance %n");
        for (Map<String,Object> transactionObj : TresactionListTemp) {
            System.out.printf("%s | %d           | %d%n", transactionObj.get("transaction_date"), transactionObj.get("amount"), transactionObj.get("balance"));

        }
    }
    
    public  List<Map<String,Object>>  getTransactionByAccount(Account account) {
        List<Transaction> transactonList=transactionrepo.findByAccount(account);
        var balance=0;
        List<Map<String,Object>> TresactionListTemp=new ArrayList<>();
        Map<String,Object> TresactionObjectTemp=new HashMap<>();
        
        var amount=0;
        for (Transaction transaction : transactonList) {
            amount=transaction.getTransactionType()==TransactionType.DEPOSIT? transaction.getAmount():-transaction.getAmount();
            balance +=amount;
            TresactionObjectTemp.put("transaction_date",transaction.getTransactionDate());
            TresactionObjectTemp.put("amount",amount);
            TresactionObjectTemp.put("balance",balance);
            TresactionListTemp.add(0,TresactionObjectTemp);
            TresactionObjectTemp=new HashMap<>();
        }
        return TresactionListTemp;
    }
}
