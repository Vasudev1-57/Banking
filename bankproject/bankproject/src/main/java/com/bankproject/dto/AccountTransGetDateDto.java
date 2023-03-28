package com.bankproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountTransGetDateDto {

    private LocalDateTime transactionDateStart;

    private LocalDateTime transactionDateEnd;
}
