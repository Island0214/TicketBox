package com.example.dao;

import com.example.bean.DoubleInfoBean;
import com.example.bean.IntInfoBean;
import com.example.bean.OrderTypeBean;
import com.example.model.MyOrder;
import com.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by island on 2018/3/19.
 */
public interface OrderRepository extends JpaSpecificationExecutor<MyOrder>, JpaRepository<MyOrder, Long> {
    @Query(value = "select order from MyOrder order where order.order_id = :id")
    MyOrder findById(@Param("id") int id);

    List<MyOrder> findByUsername(String username);

    List<MyOrder> findByUsernameAndType(String username, String type);

    @Query(value = "SELECT new com.example.bean.OrderTypeBean(o.type, count(o)) from MyOrder o where o.username = :username GROUP BY o.type")
    List<OrderTypeBean> findOrderStatistics(@Param("username") String username);

    @Query(value = "select new com.example.bean.DoubleInfoBean(" +
            "substring(o.time, 1, 7), sum(o.price)" +
            ") from MyOrder o " +
            "where o.username = :username and o.type <> '已退订订单'" +
            "GROUP BY substring(o.time, 1, 7)")
    List<DoubleInfoBean> findConsumptionStatistics(@Param("username") String username);

    @Query(value = "select o from MyOrder o where o.type = '待付款订单'")
    List<MyOrder> findAllUnpaidOrders();

    @Query(value = "select new com.example.bean.IntInfoBean(" +
            "substring(o.time, 1, 10), count(o)" +
            ") from MyOrder o " +
            "where o.venue = :venue " +
            "GROUP BY substring(o.time, 1, 10)")
    List<IntInfoBean> findDailyByVenue(@Param("venue") int venue);

    List<MyOrder> findByVenueAndType(int venue, String type);

    List<MyOrder> findByScheduleAndType(int schedule, String type);
}
