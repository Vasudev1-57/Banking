package com.bankproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="piggybank_transaction")
public class PiggyBankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int user_id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id",referencedColumnName = "transaction_id")
    private AccountTransaction accountTransaction ;

    @Column(nullable = false)
    private double amount;
    @NotNull
    private LocalDateTime transactiondate;
}
