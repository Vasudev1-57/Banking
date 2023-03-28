package com.bankproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    private int id;

    @Column(nullable = false)
        private String bank_name;
    @Column(nullable = false)

    private String ifsc_code;
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
    @Column(nullable = false)
    private String accountNo;
@Enumerated(EnumType.STRING)
    private AccountType  accountType ;
    @Column(nullable = false)
      private float  amount;

     private boolean isactive;

     private String createdby;
    @NotNull
     private LocalDateTime createdon;



}
