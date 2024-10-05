package com.skypay.skypay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skypay.skypay.model.Account;
import com.skypay.skypay.model.Transaction;
import com.skypay.skypay.service.TransactionService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionControllers {

    @Autowired
    TransactionService transactionService;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
   
    @PostMapping("/dispot/{amount}")
    public Transaction DispotTransaction(@PathVariable int amount){
        try{
           
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Account creator = (Account) authentication.getPrincipal();
            return transactionService.deposit(amount, creator);
        }
        catch(Exception e){
            logger.error("Error during rdispot", e);

        }
       
        return null;
    }



    @PostMapping("/withdraw/{amount}")
    public Transaction WithdrawTransaction(@PathVariable int amount){
        try{
           
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account creator = (Account) authentication.getPrincipal();
        return transactionService.withdraw(amount, creator);
        }
        catch(Exception e){
            logger.error("Error during withdraw", e);

        }
    
        return null;
    }

    
    @GetMapping("/getall")
    public  List<Map<String,Object>>  getAll(){
        try{
           
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account creator = (Account) authentication.getPrincipal();
        System.out.println(creator);
        return transactionService.getTransactionByAccount(creator);
        }
        catch(Exception e){
            logger.error("Error during get Transactions", e);

        }
    
        return null;
    }
    @GetMapping("/print")
    public  void  printStatement(){
        try{
           
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account creator = (Account) authentication.getPrincipal();
        System.out.println(creator);
         transactionService.printStatement(creator);
        }
        catch(Exception e){
            logger.error("Error during printing", e);

        }
    
    }

    
}
