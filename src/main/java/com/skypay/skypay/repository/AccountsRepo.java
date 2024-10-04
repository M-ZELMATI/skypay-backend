package com.skypay.skypay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skypay.skypay.model.Account;

public interface AccountsRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
