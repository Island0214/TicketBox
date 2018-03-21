package com.example.dao;

import com.example.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by island on 2018/3/21.
 */
@Repository
public interface CouponRepository extends JpaSpecificationExecutor<Coupon>, JpaRepository<Coupon, Long> {
    Coupon findById(int id);
}
