package com.bankproject.controller;

import com.bankproject.dto.AccountTransDto;
import com.bankproject.dto.AccountTransGetDateDto;
import com.bankproject.entity.Account;
import com.bankproject.entity.AccountTransaction;
import com.bankproject.entity.TransferType;
import com.bankproject.repository.AccountRepository;
import com.bankproject.repository.AccountTransRepository;
import com.bankproject.service.AccountTransService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/accountTrans-api/accountTrans/")
public class AccountTransController {

    @Autowired
    private AccountTransService accountTransService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransRepository accountTransRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> CreateTransaction(@Valid @RequestBody AccountTransDto accountTransDto) {
        Account accountfrom = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo());
        if (!accountfrom.isIsactive() ){
            return new ResponseEntity<>("From Account is currently Non Active", HttpStatus.FAILED_DEPENDENCY );
        }

        Account accountto = accountRepository.findByAccountNo(accountTransDto.getToAccountNo() );
        if (!accountto.isIsactive() ){
            return new ResponseEntity<>("To Account is currently Non Active", HttpStatus.FAILED_DEPENDENCY );
        }
        if (!TransferType.DEPOSIT.equals(accountTransDto.getTransferType())) {
            Account account1 = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo());
            if (account1.getAmount() < accountTransDto.getTransactionAmount()) {
                return new ResponseEntity<>("There are insufficient funds in your account", HttpStatus.FAILED_DEPENDENCY );
            }

        }
        List<AccountTransaction> accountTransaction = accountTransService.CreateTransaction(accountTransDto);
        return new ResponseEntity<>(accountTransaction, HttpStatus.OK);


    }

    @GetMapping("/all_transactions")
    private ResponseEntity<List<AccountTransaction>> GetAllTransactions() {
        List<AccountTransaction> accountTransactions = accountTransService.GetAllTransactions();
        return new ResponseEntity<>(accountTransactions, HttpStatus.OK);
    }

    @PostMapping("/get_transaction")
    public ResponseEntity<List<AccountTransaction>> GetTransactionByDate(@RequestBody AccountTransGetDateDto accountTransGetDateDto) {
        List<AccountTransaction> accountTransactions = accountTransService.GetTransactionByDate(accountTransGetDateDto);
        return new ResponseEntity<>(accountTransactions, HttpStatus.OK);

    }
//@GetMapping("/get_transaction")
//public ResponseEntity< List<AccountTransaction  >  > GetTransactionByDate(@RequestParam LocalDateTime transFromDate, LocalDateTime transToDate)   {
//
//    return new ResponseEntity<List<AccountTransaction>>(accountTransRepository.findByTransactionDateBetween(transFromDate,transToDate)  , HttpStatus.OK  );
//
//}

}
