package com.example.dao;

import com.example.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by island on 2018/3/16.
 */
@Repository
public interface ManagerRepository extends JpaSpecificationExecutor<Manager>, JpaRepository<Manager, Long> {
    Manager findByUsernameAndPassword(String username, String password);
}
