package com.bankproject.service;

import com.bankproject.dto.AccountTransDto;
import com.bankproject.dto.AccountTransGetDateDto;
import com.bankproject.entity.*;
import com.bankproject.repository.AccountRepository;
import com.bankproject.repository.AccountTransRepository;
import com.bankproject.repository.PiggyBankRepository;
import com.bankproject.repository.PiggyBankTransRepository;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;
import java.text.ParseException;
@Service
public class AccountTransService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PiggyBankRepository  piggyBankRepository ;

    @Autowired
    private AccountTransRepository accountTransRepository ;
    @Autowired
    private PiggyBankTransRepository piggyBankTransRepository;


    public List<AccountTransaction> CreateTransaction(AccountTransDto accountTransDto )
    {
        LocalDateTime con = LocalDateTime.now();

        List<AccountTransaction> accountTransaction1 = new ArrayList<AccountTransaction>() ;
        if (accountTransDto.getTransferType() == TransferType.DEPOSIT || accountTransDto.getTransferType() == TransferType.ATM_WITHDRAW   )
        {
            AccountTransaction accountTransaction = new AccountTransaction();
            accountTransaction.setTransactionDate(con) ;
            double doubleNumber = accountTransDto.getTransactionAmount() ;

            String doubleAsString = String.valueOf(doubleNumber);
            int indexOfDecimal = doubleAsString.indexOf(".");
            double  amnt=Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String ramnt1 = doubleAsString.substring(indexOfDecimal );
            double  Ramnt=  Double.parseDouble(ramnt1);
            double roundedamount=0;
            if (Ramnt <  0.25) {
                amnt= amnt+0.25;
                roundedamount=    0.25-Ramnt  ;

            } else if (Ramnt >   0.25 && Ramnt <  0.50) {
                amnt= amnt+0.50;
                roundedamount=    0.50-Ramnt  ;
            }
            else if (Ramnt >   0.50 && Ramnt <   0.75) {
                amnt= amnt+0.75;
                roundedamount=    0.75-Ramnt  ;
            }
            else
            {
                amnt= amnt+1;
                roundedamount=  1-Ramnt  ;
            }
//            Optional<Account> optional = accountTransRepository.findById(accountTransDto.getAccount().getId());
//            accountTransaction.setAccount(optional.get()) ;



            if (TransferType .DEPOSIT.equals(accountTransDto.getTransferType() )) {
                accountTransaction.setTransactionType(TransactionType.CREDIT  ) ;
                Account  account1 = accountRepository.findByAccountNo(accountTransDto.getToAccountNo() ) ;
                accountTransaction.setAccount(account1) ;
                accountTransaction.setTransferType(TransferType.DEPOSIT) ;
                accountTransaction.setFromAccountNo("" ) ;
                accountTransaction.setToAccountNo(accountTransDto.getToAccountNo() ) ;
            }
            else if (TransferType.ATM_WITHDRAW .equals(accountTransDto.getTransferType() )) {
                accountTransaction.setTransactionType(TransactionType.DEBIT) ;
                Account  account1 = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo() ) ;
                accountTransaction.setAccount(account1) ;
                accountTransaction.setTransferType(TransferType.ATM_WITHDRAW ) ;
                accountTransaction.setFromAccountNo(accountTransDto.getFromAccountNo() ) ;
                accountTransaction.setToAccountNo("" ) ;

            }


            accountTransaction.setNarration(accountTransDto.getNarration() ) ;
            accountTransaction.setStatus("Completed") ;
            accountTransaction.setTransactionAmount(accountTransDto.getTransactionAmount() ) ;
            accountTransRepository .save(accountTransaction);
            accountTransaction1.add(accountTransaction);

            Account account1 = null;


            if (TransferType .DEPOSIT.equals(accountTransDto.getTransferType() )) {
                  account1 = accountRepository.findByAccountNo(accountTransDto.getToAccountNo());


            }
            else {
                  account1 = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo() );
                PiggyBank piggyBank = piggyBankRepository.findByUser_Id(account1.getUser().getId()) ;
                piggyBank.setBalance(roundedamount + piggyBank.getBalance() );
                piggyBank.setUpdatedon(con);
                piggyBank.setCreatedon(con) ;
                piggyBankRepository.save(piggyBank);

                PiggyBankTransaction  piggyBankTransaction = new PiggyBankTransaction();
                piggyBankTransaction.setUser_id(account1.getUser().getId() );
                piggyBankTransaction.setAccountTransaction(accountTransaction ) ;
                piggyBankTransaction.setAmount(roundedamount);
                piggyBankTransaction.setTransactiondate(con) ;
                piggyBankTransRepository .save(piggyBankTransaction);
            }



            if (accountTransDto.getTransferType() == TransferType.DEPOSIT    )
            {
                Account  account = accountRepository.findByAccountNo(accountTransDto.getToAccountNo()  );
                account.setAmount((float) (amnt + account.getAmount()));
                accountRepository.save(account);

            } else if (accountTransDto.getTransferType() == TransferType.ATM_WITHDRAW  ) {
                Account  account = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo()  );
                account.setAmount(account.getAmount() - accountTransDto.getTransactionAmount());
                accountRepository.save(account);
            }

        }

        else {

            double doubleNumber = accountTransDto.getTransactionAmount() ;
            String doubleAsString = String.valueOf(doubleNumber);
            int indexOfDecimal = doubleAsString.indexOf(".");
            double  amnt=Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String ramnt1 = doubleAsString.substring(indexOfDecimal );
            double  Ramnt=  Double.parseDouble(ramnt1);
            double roundedamount=0;
            if (Ramnt <  0.25) {
                amnt= amnt+0.25;
                roundedamount=    0.25-Ramnt  ;

            } else if (Ramnt >   0.25 && Ramnt <  0.50) {
                amnt= amnt+0.50;
                roundedamount=    0.50-Ramnt  ;
            }
            else if (Ramnt >   0.50 && Ramnt <   0.75) {
                amnt= amnt+0.75;
                roundedamount=    0.75-Ramnt  ;
            }
            else
            {
                amnt= amnt+1;
                roundedamount=  1-Ramnt  ;
            }
            for(int i=0; i<2 ; i++){

                AccountTransaction accountTransaction = new AccountTransaction();
            accountTransaction.setTransactionDate(con) ;
//            Optional<Account> optional = accountTransRepository.findById(accountTransDto.getAccount().getId());
//            accountTransaction.setAccount(optional.get()) ;

            if (i==0) {
                accountTransaction.setTransactionType(TransactionType.DEBIT) ;
                Account account1 = null;
                   account1 = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo() ) ;
                accountTransaction.setAccount(account1) ;
            }
            else {
                accountTransaction.setTransactionType(TransactionType.CREDIT  ) ;
                Account account1 = null;
                   account1 = accountRepository.findByAccountNo(accountTransDto.getToAccountNo() ) ;
                accountTransaction.setAccount(account1) ;
            }

         if (TransferType.UPI_TRANSFER  .equals(accountTransDto.getTransferType() )) {
                accountTransaction.setTransferType(TransferType.UPI_TRANSFER ) ;
            }
            else if (TransferType.BANK_TRANSFER   .equals(accountTransDto.getTransferType() )) {
                accountTransaction.setTransferType(TransferType.BANK_TRANSFER ) ;
            }
            accountTransaction.setFromAccountNo(accountTransDto.getFromAccountNo() ) ;
            accountTransaction.setToAccountNo(accountTransDto.getToAccountNo() ) ;
            accountTransaction.setNarration(accountTransDto.getNarration() ) ;
            accountTransaction.setStatus("Completed" ) ;
            accountTransaction.setTransactionAmount(accountTransDto.getTransactionAmount() ) ;
            accountTransRepository .save(accountTransaction);
                accountTransaction1.add(accountTransaction);
                Account account1 = null;

                if (i==1)
                {
                    account1 = accountRepository.findByAccountNo(accountTransDto.getToAccountNo());

                }
                else {
                    account1 = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo() );
                    PiggyBank piggyBank = piggyBankRepository.findByUser_Id(account1.getUser().getId() ) ;
                    piggyBank.setBalance(roundedamount + piggyBank.getBalance() );
                    piggyBank.setUpdatedon(con);
                    piggyBank.setCreatedon(con) ;
                    piggyBankRepository.save(piggyBank);


                    PiggyBankTransaction  piggyBankTransaction = new PiggyBankTransaction();
                    piggyBankTransaction.setUser_id(account1.getUser().getId() );
                    piggyBankTransaction.setAccountTransaction(accountTransaction ) ;
                    piggyBankTransaction.setAmount(roundedamount);
                    piggyBankTransaction.setTransactiondate(con) ;
                    piggyBankTransRepository .save(piggyBankTransaction);
                }

                if (i==1)
                {
                    Account  account = accountRepository.findByAccountNo(accountTransDto.getToAccountNo()  );
                    account.setAmount((float) (amnt + account.getAmount()));
                    accountRepository.save(account);

                } else  {
                    Account  account = accountRepository.findByAccountNo(accountTransDto.getFromAccountNo()  );
                    account.setAmount((float) (account.getAmount()- amnt));
                    accountRepository.save(account);
                }

            }
        }


        return accountTransaction1;

    }

    public List<AccountTransaction  > GetAllTransactions() {
        return accountTransRepository .findAll();
    }

    public  List<AccountTransaction>  GetTransactionByDate(AccountTransGetDateDto accountTransGetDateDto )  {
//

         return accountTransRepository.findByTransactionDateBetween(accountTransGetDateDto.getTransactionDateStart() ,accountTransGetDateDto.getTransactionDateStart() );


    }

}
