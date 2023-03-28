package com.bankproject.controller;

import com.bankproject.dto.AccountDto;
import com.bankproject.dto.UserDto;
import com.bankproject.entity.Account;
import com.bankproject.entity.User;
import com.bankproject.repository.AccountRepository;
import com.bankproject.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account-api/account")
public class AccountsController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository ;
    @PostMapping("/create")
    public ResponseEntity<Object> CreateAccount(@Valid @RequestBody AccountDto accountDto )
    {
        if(accountRepository.findByAccountNo(accountDto.getAccountNo() ) == null ){
            Account  account   = accountService.CreateAccount(accountDto ) ;
            return new ResponseEntity<>(account , HttpStatus.CREATED );
        }
        else {
            return new ResponseEntity<>("Already This Email Id is available", HttpStatus.OK  );
        }
    }

    @PostMapping("/update")
    private ResponseEntity<Account > UpdateAccount(@Valid @RequestBody AccountDto  accountDto ) {
        Account  account = accountService .UpdateAccount(accountDto ) ;
        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @GetMapping("/all_accounts")
    private ResponseEntity<List<Account>> GetAllAccounts() {
        List<Account> account = accountService.GetAllAccounts() ;
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/get_account/{id}")
    public Optional<Account> GetAccountById(@PathVariable("id") int id) {
        return accountService.GetAllAccountsById(id) ;

    }

}
