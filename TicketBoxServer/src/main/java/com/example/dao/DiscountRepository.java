package com.example.dao;

import com.example.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by island on 2018/3/19.
 */
@Repository
public interface DiscountRepository extends JpaSpecificationExecutor<Discount>, JpaRepository<Discount, Long> {
    @Query(value = "select d from Discount d where d.consumption <= :consumption order by d.grade desc")
    List<Discount> findGradeByConsumption(@Param("consumption") int consumption);

    Discount findByGrade(int grade);
}
