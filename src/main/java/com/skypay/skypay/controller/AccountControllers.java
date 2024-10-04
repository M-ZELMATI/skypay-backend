package com.skypay.skypay.controller;

import com.skypay.skypay.model.Account;
import com.skypay.skypay.service.AccountsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AccountControllers {

    @Autowired
    private AccountsService authService;

    @PostMapping("/create")
    public Account signUp(@RequestBody Account signUpRequest){
        return authService.creatUser(signUpRequest);
    }
    @PostMapping("/signin")
    public Map<String,Object> signIn(@RequestBody Account signInRequest){
        return authService.signIn(signInRequest);
    }
   
    @GetMapping("/all")
    public List<Account> getAllAccount() {
        try {
            List<Account> Account = authService.getAllAccount();
            return Account;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
}