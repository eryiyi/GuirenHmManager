package com.liangxunwang.unimanager.service.app;


import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/15.
 */
@Service("appOrderService")
public class AppOrderService implements UpdateService,SaveService,ExecuteService {
//    private static Logger logger = LogManager.getLogger(AppOrderService.class);
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeSaveDao;

    @Override
    public Object update(Object object) {
        Map<String, String> map = (Map<String, String>) object;
        String order_no = map.get("order_no");
        String status = map.get("status");
        if("3".equals(status)){
            // 取消订单
            appOrderMakeSaveDao.cancelOrderById(order_no);
        }
        if("4".equals(status)){
            //删除订单
            appOrderMakeSaveDao.deleteOrderById(order_no);
        }
        return null;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Map<String,String> map = (Map<String, String>) object;
        String order_no = map.get("order_no");
        String doublePrices = map.get("doublePrices");
        //生成sign签名
        // 订单
        String orderInfo = StringUtil.getOrderInfo(order_no, "paopaojianghu", "isbody", String.valueOf(doublePrices));

        // 对订单做RSA 签名
        String sign = StringUtil.sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
            return new OrderInfoAndSign(orderInfo, sign, order_no);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("ISWRONG");
        }
    }

    //查询订单数量
    @Override
    public Object execute(Object object) throws ServiceException {
        Map<String,Object> mapOld = (Map<String, Object>) object;
        Map<String,Object> map1 = new HashMap<String, Object>();
        Map<String,Object> map2 = new HashMap<String, Object>();
        Map<String,Object> map3 = new HashMap<String, Object>();
        Map<String,Object> map4 = new HashMap<String, Object>();
        String empId = (String) mapOld.get("emp_id");//用户id
        String time_status = (String) mapOld.get("time_status");//时间0 今天  1代表总的  2查询今天的收入
        if("0".equals(time_status)){
            //查询今日订单总数量，全部状态的
            map1.put("emp_id", empId);
            map1.put("start", DateUtil.getStartDay()+"");
            map1.put("end", DateUtil.getEndDay()+"");
            long number = appOrderMakeSaveDao.selectOrderNumByDay(map1);
            return String.valueOf(number);
        }else if("1".equals(time_status)){
            //查询总的订单数量
            map2.put("emp_id", empId);
            long number = appOrderMakeSaveDao.selectOrderNum(map2);
            return String.valueOf(number);
        }else if("2".equals(time_status)){
            //查询今天收入
            map3.put("emp_id", empId);
            map3.put("start", DateUtil.getStartDay()+"");
            map3.put("end", DateUtil.getEndDay()+"");
            String floatSum = appOrderMakeSaveDao.selectSum(map3);
            return floatSum;
        }else if("3".equals(time_status)){
            //查询总收入
            map4.put("emp_id", empId);
            String floatSum = appOrderMakeSaveDao.selectSum(map4);
            return floatSum;
        }
        return null;
    }

}
