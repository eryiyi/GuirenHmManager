package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.model.WxPayObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.OrderVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/13.
 */
@Controller
public class AppOrderMakeController extends ControllerConstants {

    @Autowired
    @Qualifier("appOrderMakeService")
    private SaveService appOrderMakeService;

    @Autowired
    @Qualifier("appOrderMakeWxService")
    private SaveService appOrderMakeWxServiceSave;

    @Autowired
    @Qualifier("appOrderMakeService")
    private UpdateService appOrderUpdateService;

    @Autowired
    @Qualifier("appOrderMakeService")
    private ListService appOrderListService;

    @Autowired
    @Qualifier("appOrderMakeService")
    private FindService appOrderFindService;

    @Autowired
    @Qualifier("appOrderService")
    private UpdateService UpateappOrderService;

    @Autowired
    @Qualifier("appOrderService")
    private SaveService SaveappOrderService;

    @Autowired
    @Qualifier("appOrderService")
    private ExecuteService executeOrderService;


    /**
     * 订单接收---形成订单
     * @return
     */
    @RequestMapping("/orderSave")
    @ResponseBody
    public String orderSave(Order order){
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderMakeService.save(order);
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
        }
        return null;
    }

    /**
     * 订单接收---形成订单 ----- 微信
     * @return
     */
    @RequestMapping("/orderSaveWx")
    @ResponseBody
    public String orderSaveWx(Order order){
        try {
            Object[] strs = (Object[]) appOrderMakeWxServiceSave.save(order);
            WxPayObj wxPayObj = new WxPayObj();
            wxPayObj.setXmlStr((String) strs[0]);
            wxPayObj.setOut_trade_no((String) strs[1]);
            DataTip tip = new DataTip();
            tip.setData(wxPayObj);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
        }
        return null;
    }

//    /**
//     * 订单接收---形成订单 ----- 微信----统一下单反馈
//     * @return
//     */
//    @RequestMapping("/orderSaveWxFk")
//    @ResponseBody
//    public String orderSaveWxFk(){
//       //逻辑处理
//        return null;
//    }
//
//    /**
//     * 订单接收---形成订单 ----- 支付宝----统一下单反馈
//     * @return
//     */
//    @RequestMapping("/orderSaveZfbFk")
//    @ResponseBody
//    public String orderSaveZfbFk(){
//        //逻辑处理
//        return null;
//    }

    /**
     * 订单更新，支付订单成功
     * @param order
     * @return
     */
    @RequestMapping("/orderUpdate")
    @ResponseBody
    public String orderUpdate(Order order){
        appOrderUpdateService.update(order);
        return toJSONString(SUCCESS);
    }

    /**
     * 查询订单列表--会员查询
     * */
    @RequestMapping(value = "/listOrders", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listGoodsByType(OrdersQuery query, Page page){
        try {
            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
            List<OrderVO> list = (List<OrderVO>) appOrderListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    //取消订单  确认收货  更改订单状态
    @RequestMapping("/updateOrder")
    @ResponseBody
    public String cancleOrder(String order_no ,String status){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no",order_no);
        map.put("status",status);
        UpateappOrderService.update(map);
        return toJSONString(SUCCESS);
    }

    /**
     * 订单接收--补款--单个订单
     * @return
     */
    @RequestMapping("/orderSaveSingle")
    @ResponseBody
    public String orderSaveSingle(String order_no, String doublePrices){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) SaveappOrderService.save(map);
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
        }
        return null;
    }


}
