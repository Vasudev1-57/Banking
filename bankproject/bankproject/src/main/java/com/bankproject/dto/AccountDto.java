package com.bankproject.dto;

import com.bankproject.entity.AccountType;
import com.bankproject.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountDto {
    @NotBlank(message = "Bank Name is Required")
    private String bank_name;

    @NotBlank(message = "IFSCCode is Required")
    private String ifsc_code;

    private User  user;

    private String accountNo;
    private AccountType accountType ;

    private float  amount;

    private Boolean isactive;

    private String createdby;
    private LocalDateTime createdon;


}
