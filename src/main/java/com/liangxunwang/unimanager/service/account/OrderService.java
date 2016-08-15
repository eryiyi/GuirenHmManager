package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.OrderDao;
import com.liangxunwang.unimanager.mvc.vo.OrderVO;
import com.liangxunwang.unimanager.query.OrderQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("orderService")
public class OrderService implements ListService {
    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Override
    public Object list(Object object) throws ServiceException {
        OrderQuery query = (OrderQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getStatus())) {
            map.put("status", query.getStatus());
        }

        List<OrderVO> lists = orderDao.listOrders(map);
        long count = orderDao.count(map);
        for(OrderVO orderVO:lists){
            if(!StringUtil.isNullOrEmpty(orderVO.getCreate_time())){
                orderVO.setCreate_time(DateUtil.getDate(orderVO.getCreate_time(), "yyyy-MM-dd HH:mm:ss") + "");
            }
            if(!StringUtil.isNullOrEmpty(orderVO.getPay_time())){
                orderVO.setPay_time(DateUtil.getDate(orderVO.getPay_time(), "yyyy-MM-dd HH:mm:ss") + "");
            }
        }
        return new Object[]{lists, count};
    }

}
