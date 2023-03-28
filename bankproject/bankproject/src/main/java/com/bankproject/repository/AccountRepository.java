package com.bankproject.repository;

import com.bankproject.entity.Account;
import com.bankproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByAccountNo(String accountNo);



}
