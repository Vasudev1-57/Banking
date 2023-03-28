package com.bankproject.service;

import com.bankproject.dto.UserDto;
import com.bankproject.entity.PiggyBank;
import com.bankproject.entity.User;
import com.bankproject.repository.PiggyBankRepository;
import com.bankproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PiggyBankRepository piggyBankRepository;

    public User CreateUser(UserDto userDto) {


                User user = new User();
                user.setName(userDto.getName());
                user.setAddress(userDto.getAddress());
                user.setMobileNo(userDto.getMobileNo());
                user.setAadhaarNo(userDto.getAadhaarNo());
                user.setDob(userDto.getDob());
                user.setEmailId(userDto.getEmailId());
                userRepository.save(user);

//
//        User user1 = userRepository.findByEmailId(userDto.getEmailId() ) ;

                PiggyBank piggyBank = new PiggyBank();
                piggyBank.setUser(user);
                piggyBank.setBalance(0);
                LocalDateTime con = LocalDateTime.now();
                piggyBank.setCreatedon(con);
                piggyBank.setUpdatedon(con);
                piggyBankRepository.save(piggyBank);
                return user;


    }

    public User UpdateUser(UserDto userDto) {
        String ev=userDto.getEmailId();

        User user = userRepository.findByEmailId(userDto.getEmailId() ) ;
        System.out.println(ev);
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setMobileNo(userDto.getMobileNo());
        user.setAadhaarNo(userDto.getAadhaarNo() );
        user.setDob(userDto.getDob());
        user.setEmailId(userDto.getEmailId());
        userRepository.save(user);
        return user;
    }


    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> GetUsersById(int id) {
        return userRepository.findById(id);

    }

}

