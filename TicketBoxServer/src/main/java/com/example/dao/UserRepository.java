package com.example.dao;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by island on 2018/3/14.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //可以根据方法名进行衍生查询，也可以自定义查询。
    @Query("select u from User u where u.username=?1")
    List<User> findByLastname(String username);

    Long countByLastname(String lastname);
}