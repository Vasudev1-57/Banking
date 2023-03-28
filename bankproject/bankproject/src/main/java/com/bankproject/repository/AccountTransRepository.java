package com.bankproject.repository;

import com.bankproject.entity.Account;
import com.bankproject.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.*;

@Repository
public interface AccountTransRepository extends JpaRepository<AccountTransaction,Integer > {
    Optional<Account> findById(int id);

   
      @Query("select a from AccountTransaction a where a.transactionDate between ?1 and ?2")
  List<AccountTransaction>  findByTransactionDateBetween(LocalDateTime transactionDateStart, LocalDateTime transactionDateEnd);


   // List<AccountTransaction>  findByTransactionDateBetween(LocalDateTime  transactionDateStart, LocalDateTime transactionDateEnd);


//    AccountTransaction findByTransactionDate(LocalDateTime transactionDate);


     //AccountTransaction findByTransactionDateAndTransactionDate(Date transactionDate, Date transactionDate1);




}
