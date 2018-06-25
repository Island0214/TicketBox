package com.example.dao;

import com.example.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qianzhihao
 * @version 2018/6/25
 */
public interface AdvertisementRepository extends JpaRepository<Advertisement,Integer>{
}
