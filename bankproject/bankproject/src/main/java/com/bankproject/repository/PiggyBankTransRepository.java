package com.bankproject.repository;

import com.bankproject.entity.PiggyBankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PiggyBankTransRepository extends JpaRepository<PiggyBankTransaction ,Integer > {

}
