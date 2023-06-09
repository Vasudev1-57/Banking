package com.bankproject.repository;

import com.bankproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository  extends JpaRepository<User,Integer >{
    User findByEmailId(String EmailId);


}
