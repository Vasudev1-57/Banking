package com.bankproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transactions")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int transaction_id;
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransferType transferType ;
     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "account_id",referencedColumnName = "id")
     private Account account;
     private String fromAccountNo;

    private String toAccountNo;
    @Column(nullable = false)
    private String narration;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private float transactionAmount ;

}
