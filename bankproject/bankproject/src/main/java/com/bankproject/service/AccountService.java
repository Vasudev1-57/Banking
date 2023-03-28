package com.bankproject.service;

import com.bankproject.dto.AccountDto;
import com.bankproject.dto.UserDto;
import com.bankproject.entity.Account;
import com.bankproject.entity.AccountType;
import com.bankproject.entity.PiggyBank;
import com.bankproject.entity.User;
import com.bankproject.repository.AccountRepository;
import com.bankproject.repository.PiggyBankRepository;
import com.bankproject.repository.UserRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository ;
    @Autowired
    private PiggyBankRepository piggyBankRepository;
    @Autowired
    private UserRepository userRepository ;

    public Account CreateAccount(AccountDto  accountDto ) {
        Account account  = new Account ();
        account.setBank_name(accountDto.getBank_name() ) ;
        account.setIfsc_code(accountDto.getIfsc_code()) ;

        Optional<User> optional = userRepository.findById(accountDto.getUser().getId());
        account.setUser(optional.get()) ;
        int min = 111111111;
        int max = 999999999;
        int RandomAccountNo = (int)(Math.random()*(max-min+1)+min);
        String AccountNumber = Integer.toString(RandomAccountNo);

        account.setAccountNo(AccountNumber) ;
        if (AccountType.SAVINGS.equals(accountDto.getAccountType())) {
            account.setAccountType(AccountType.SAVINGS) ;
        }
        else {
            account.setAccountType(AccountType.CURRENT ) ;
        }


        account.setAmount(accountDto.getAmount()) ;

        account.setIsactive(accountDto.getIsactive() ) ;
        account.setCreatedby(accountDto.getCreatedby() ) ;
        LocalDateTime con = LocalDateTime.now();
        account.setCreatedon(con) ;
        accountRepository.save(account);
        return account;
    }

    public Account  UpdateAccount(AccountDto accountDto ) {

        Account  account = accountRepository.findByAccountNo(accountDto.getAccountNo() ) ;
        Optional<User> optional = userRepository.findById(accountDto.getUser().getId());
        account.setUser(optional.get()) ;

        account.setBank_name(accountDto.getBank_name() ) ;
        account.setIfsc_code(accountDto.getIfsc_code()) ;
        //account.setUser(accountDto.getUser() ) ;

        if (AccountType.SAVINGS.equals(accountDto.getAccountType())) {
            account.setAccountType(AccountType.SAVINGS) ;
        }
        else {
            account.setAccountType(AccountType.CURRENT ) ;
        }

        account.setAmount(accountDto.getAmount()) ;

        account.setIsactive(accountDto.getIsactive() ) ;
        account.setCreatedby(accountDto.getCreatedby() ) ;
        LocalDateTime con = LocalDateTime.now();
        account.setCreatedon(con) ;
        accountRepository.save(account);

        return account;
    }
    public List<Account > GetAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> GetAllAccountsById(int id) {
        return accountRepository.findById(id);

    }
}
