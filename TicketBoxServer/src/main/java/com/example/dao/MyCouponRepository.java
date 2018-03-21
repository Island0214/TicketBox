package com.example.dao;

import com.example.model.MyCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by island on 2018/3/21.
 */
public interface MyCouponRepository extends JpaSpecificationExecutor<MyCoupon>, JpaRepository<MyCoupon, Long> {
    List<MyCoupon> findByUsernameAndUsed(String username, boolean used);
}
