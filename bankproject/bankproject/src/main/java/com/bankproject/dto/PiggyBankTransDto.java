package com.bankproject.dto;

import com.bankproject.entity.AccountTransaction;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PiggyBankTransDto {
    private int id;
    private int user_id;
    private AccountTransaction accountTransaction ;
    private double amount;
    private LocalDateTime transactiondate;
}
