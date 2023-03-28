package com.bankproject.repository;

import com.bankproject.entity.PiggyBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiggyBankRepository extends JpaRepository<PiggyBank,Integer> {
     PiggyBank  findByUser_Id(int id);
}
