package com.bankproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDto {
    @NotBlank(message = "Name is Required")
    private String name;
    @NotBlank(message = "Address is Required")
    private String address;
    private Date dob;

    private long mobileNo;
    @Size(min=10 , max=12,message="Aadhaar no must be greater than 10 and less than 12")
    private String aadhaarNo;
    @Email(message = "Enter valid Email Id")
    private String emailId;
}
