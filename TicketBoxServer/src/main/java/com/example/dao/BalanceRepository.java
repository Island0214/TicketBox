package com.example.dao;

import com.example.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by island on 2018/3/21.
 */
@Repository
public interface BalanceRepository extends JpaSpecificationExecutor<Balance>, JpaRepository<Balance, Long> {
    List<Balance> findByVenue(int venue);
}
