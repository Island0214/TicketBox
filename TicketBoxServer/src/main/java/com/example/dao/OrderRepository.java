package com.example.dao;

import com.example.model.MyOrder;
import com.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by island on 2018/3/19.
 */
public interface OrderRepository extends JpaSpecificationExecutor<MyOrder>, JpaRepository<MyOrder, Long> {
    @Query(value = "select order from MyOrder order where order.order_id = :id")
    MyOrder findById(@Param("id") int id);
}
