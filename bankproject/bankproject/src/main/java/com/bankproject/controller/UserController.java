package com.bankproject.controller;

import com.bankproject.dto.UserDto;
import com.bankproject.entity.User;
import com.bankproject.repository.UserRepository;
import com.bankproject.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    private ResponseEntity<Object> CreateUser(@Valid @RequestBody UserDto userDto) {

       //  if(userRepository.findByEmailId(userDto.getEmailId()) == null ){
            User user = userService.CreateUser(userDto);
            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>("Already This Email Id is available", HttpStatus.BAD_REQUEST);
//        }

    }

    @PostMapping("/update")
    private ResponseEntity<User> UpdateUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.UpdateUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  DeleteUser(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("User Code " + id + "was deleted.", HttpStatus.OK);
    }

    @GetMapping("/all_users")
    private ResponseEntity<List<User>> GetAllUsers() {
        List<User> user = userService.GetAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get_user/{id}")
    public Optional<User> GetUsersById(@PathVariable("id") int id) {
        return userService.GetUsersById(id);

    }


}
