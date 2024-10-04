package com.skypay.skypay.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skypay.skypay.model.Account;
import com.skypay.skypay.repository.AccountsRepo;

@Service
public class OurAccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountsRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Unwrap Optional<Account> and throw exception if not found
        Account account = accountRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Return the account if it implements UserDetails
        return account;
    }
}
