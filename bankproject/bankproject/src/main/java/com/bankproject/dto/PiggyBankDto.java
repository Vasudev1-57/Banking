package com.bankproject.dto;

import com.bankproject.entity.PiggyBank;
import com.bankproject.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

@Data
public class PiggyBankDto  {
    private User user;
    private double balance;

    private Date createdon;

    private Date updatedon;
}
