package com.example.dao;

import com.example.model.MyPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by island on 2018/3/24.
 */
public interface PayRepository extends JpaSpecificationExecutor<MyPay>, JpaRepository<MyPay, Long> {
    MyPay findByOrderid(int orderid);
}
