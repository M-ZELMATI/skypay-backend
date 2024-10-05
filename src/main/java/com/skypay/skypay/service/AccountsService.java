package com.skypay.skypay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.skypay.skypay.exception.JwtException;
import com.skypay.skypay.model.Account;
import com.skypay.skypay.repository.AccountsRepo;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountsService implements UserDetailsService {

    @Autowired
    private AccountsRepo accountRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + username));

        // Convert Account to UserDetails
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole())); // Assuming a single role, modify if needed

        return new User(account.getEmail(), account.getPassword(), authorities);
    }

    public Account creatUser(Account registrationRequest) {

        Account ourAccount = new Account();

        try {
            ourAccount.setName(registrationRequest.getName());
            ourAccount.setEmail(registrationRequest.getEmail());
            ourAccount.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourAccount.setRole(registrationRequest.getRole());
            ourAccount.setEnabled(registrationRequest.isEnabled());
            ourAccount.setAccountNumber(registrationRequest.getAccountNumber());

            return accountRepo.save(ourAccount);

        } catch (Exception e) {
            throw new JwtException("Sign up error: " + e.getMessage());
        }
    }

    public Map<String, Object> signIn(Account signinRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
         
            var user = accountRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            // var jwt ="";
            response.put("code", 200);
            response.put("access_token", jwt);
            response.put("exp", "24Hr");
            response.put("message", "Successfully Signed In");

        } catch (Exception e) {
            response.put("code", 500);
            response.put("error", "Authentication failed: " + e.getMessage()); // More user-friendly message
        }
        return response;
    }

 
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepo.findByEmail(email);
    }
}
