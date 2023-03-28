package com.bankproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="piggybank")
public class PiggyBank {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    @Column(nullable = false)
    private double balance;
    @NotNull
    private LocalDateTime  createdon;
    @NotNull
    private LocalDateTime updatedon;
}
