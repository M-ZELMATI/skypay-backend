package com.skypay.skypay.repository;

import com.skypay.skypay.model.Account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AccountsRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
}
