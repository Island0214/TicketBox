package com.example.dao;

import com.example.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by island on 2018/3/23.
 */
@Repository
public interface BankAccountRepository extends JpaSpecificationExecutor<BankAccount>, JpaRepository<BankAccount, Long> {
    BankAccount findByAccountAndPassword(String account, String password);
}
