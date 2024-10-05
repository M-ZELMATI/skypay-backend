package com.skypay.skypay.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypay.skypay.model.Account;
import com.skypay.skypay.repository.AccountsRepo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Data
public class Dataloader implements CommandLineRunner {
    @Autowired
    private final AccountsRepo accountRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;
 
    @Override
    public void run(String... args) throws Exception {
   
        loadAccountData();
    }

   
    private void loadAccountData() throws Exception {
        if (accountRepo.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {};
            InputStream inputStream = new ClassPathResource("Accounts.json").getInputStream();
            List<Account> Account = objectMapper.readValue(inputStream, typeReference);
            Account.forEach(user -> {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                accountRepo.save(user);
            });
        }
    }

   
}
