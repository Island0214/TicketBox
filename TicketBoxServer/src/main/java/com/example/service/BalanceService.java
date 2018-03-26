package com.example.service;

import com.example.bean.DoubleInfoBean;
import com.example.model.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by island on 2018/3/20.
 */
@Service
public interface BalanceService {
    List<Balance> getUnpaidSchedules();

    boolean payBalance(Balance balance);

    List<Balance> getPaidSchedules();

    List<DoubleInfoBean> getVenueAverageIncome();
}
