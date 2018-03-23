package com.example.dao;

import com.example.model.MyOrder;
import com.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by island on 2018/3/19.
 */
public interface OrderRepository extends JpaSpecificationExecutor<MyOrder>, JpaRepository<MyOrder, Long> {
}
