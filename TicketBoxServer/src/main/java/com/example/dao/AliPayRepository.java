package com.example.dao;

import com.example.model.AliPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by island on 2018/3/24.
 */
public interface AliPayRepository extends JpaSpecificationExecutor<AliPay>, JpaRepository<AliPay, Long> {
    AliPay findByAccountAndPassword(String account, String password);

}
