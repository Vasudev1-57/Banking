package com.bankproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",uniqueConstraints ={ @UniqueConstraint(name = "emailId_unique",columnNames = "emailId") } )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dob;
    @Column(nullable = false, length = 12)
    private long mobileNo;
    @Column(nullable = false, length = 12)
    private String aadhaarNo;

    private String emailId;


}
