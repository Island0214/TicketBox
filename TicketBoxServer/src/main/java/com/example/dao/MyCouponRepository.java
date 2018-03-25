package com.example.dao;

import com.example.model.MyCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by island on 2018/3/21.
 */
public interface MyCouponRepository extends JpaSpecificationExecutor<MyCoupon>, JpaRepository<MyCoupon, Long> {
    List<MyCoupon> findByUsernameAndUsed(String username, boolean used);

    @Query(value = "select distinct coupon.coupon from MyCoupon coupon where coupon.username = :username and coupon.used = false order by coupon.coupon")
    List<Integer> findDistinctByUsernameAndUsed(@Param("username") String username);

    List<MyCoupon> findByCouponAndUsernameAndUsed(int coupon, String username, boolean used);
}
