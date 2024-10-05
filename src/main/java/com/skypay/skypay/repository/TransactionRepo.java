
package com.skypay.skypay.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skypay.skypay.model.Account;
import com.skypay.skypay.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
     @Query(value = "select * from transaction WHERE account_id = :account_id ", nativeQuery = true)
    List<Map<String,Object>> getTransactinByAccountID(@Param("account_id") long account_id);
}
