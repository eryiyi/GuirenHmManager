package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.mvc.vo.OrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzh on 2015/8/21.
 *
 * 后台操作订单
 */
@Repository("orderDao")
public interface OrderDao {

    /**
     * 查询所有的订单
     * @param map
     * @return
     */
    List<OrderVO> listOrders(Map<String, Object> map);

    long count(Map<String, Object> map);

    /**
     * 根据订单号
     * @param id
     * @return
     */
    OrderVO findById(String id);


//    void updateOrderStatus(@Param(value = "time") String time, @Param(value = "period") String period);



}
