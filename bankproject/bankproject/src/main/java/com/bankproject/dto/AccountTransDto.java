package com.bankproject.dto;

import com.bankproject.entity.Account;
import com.bankproject.entity.TransactionType;
import com.bankproject.entity.TransferType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class AccountTransDto {

    private TransferType transferType ;

    private String fromAccountNo;

    private String toAccountNo;
    @NotBlank(message = "Narration is Required")
    private String narration;

    private String status;

    private float transactionAmount ;
}
