package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.dao.YaoqingCardDao;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.model.ShoppingTrade;
import com.liangxunwang.unimanager.model.YaoqingCard;
import com.liangxunwang.unimanager.mvc.vo.OrderVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/14.
 */
@Service("appOrderMakeService")
public class AppOrderMakeService implements SaveService,UpdateService,ListService,FindService {
//    private static Logger logger = LogManager.getLogger(AppOrderMakeService.class);
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeSaveDao;

    //保存订单
    @Override
    public Object save(Object object) throws ServiceException {
        Order order = (Order) object;
        Double doublePrices = 0.0;
        doublePrices += order.getPayable_amount();
        doublePrices = Double.parseDouble(String.format("%.2f",doublePrices));
        //商品总额，用于插入订单金额
        String out_trade_no = UUIDFactory.random();//订单总金额的id
        ShoppingTrade shoppingTrade = new ShoppingTrade();
        shoppingTrade.setOut_trade_no(out_trade_no);
        shoppingTrade.setPay_status("0");
        shoppingTrade.setDateline(System.currentTimeMillis() + "");
        shoppingTrade.setTrade_prices(String.valueOf(doublePrices));

        //保存总订单--和支付宝一致
        appOrderMakeSaveDao.saveTrade(shoppingTrade);
        //构造订单列表
        order.setOrder_no(UUIDFactory.random());
        order.setCreate_time(System.currentTimeMillis() + "");
        order.setStatus("0");//0生成订单 1支付订单
        order.setPay_status("0");//0未支付  1支付
        order.setPostscript("");
        order.setTrade_no(out_trade_no);
        order.setInvoice("");
        order.setInvoice_title("");
        order.setTaxes("");

        //保存订单
        appOrderMakeSaveDao.saveList(order);
        //生成sign签名
        // 订单
        String orderInfo = StringUtil.getOrderInfo(out_trade_no, "guiren", "guirenzhifu", String.valueOf(doublePrices));

        // 对订单做RSA 签名
        String sign = StringUtil.sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
            return new OrderInfoAndSign(orderInfo, sign, out_trade_no);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("ISWRONG");
        }
    }

    @Autowired
    @Qualifier("yaoqingCardDao")
    private YaoqingCardDao yaoqingCardDao;

    //更新订单状态
    @Override
    public Object update(Object object) {
        if (object instanceof Order){
            //跟新主订单和分订单状态
            Order order = (Order) object;
            appOrderMakeSaveDao.updateTradeById(order.getTrade_no());
            order.setPay_time(System.currentTimeMillis() + "");
            order.setStatus("1");
            order.setPay_status("1");
            appOrderMakeSaveDao.updateOrderById(order);

            //首先根据支付单号获得order
            List<Order> listOrders  =  appOrderMakeSaveDao.findOrderByTradeNo(order.getTrade_no());
            if(listOrders != null && listOrders.size()>0){
                Order order1 = listOrders.get(0);
                if(order1 != null){
                    int numCard = Integer.parseInt(order1.getGoods_count());//邀请码数量
                    for(int i=0;i<numCard;i++){
                        YaoqingCard yaoqingCard = new YaoqingCard();
                        yaoqingCard.setMm_emp_id(order1.getEmp_id());//邀请人
                        yaoqingCard.setGuiren_card_id(UUIDFactory.random());
                        yaoqingCard.setIs_use("0");
                        yaoqingCard.setGuiren_card_num(StringUtil.getStringRandom(8));
                        yaoqingCard.setCreatetime(System.currentTimeMillis() + "");
                        yaoqingCardDao.save(yaoqingCard);
                    }
                }
            }
        }else {
            //支付单一订单成功，更新分订单状态
            String order_no = (String) object;
            String pay_time =  System.currentTimeMillis() + "";
            appOrderMakeSaveDao.updateOrderBySingle(order_no, pay_time);

        }
        return null;
    }

    //查询订单列表
    @Override
    public Object list(Object object) throws ServiceException {
        OrdersQuery query = (OrdersQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if (!StringUtil.isNullOrEmpty(query.getEmp_id())) {
            map.put("emp_id", query.getEmp_id());
        }
        if (!StringUtil.isNullOrEmpty(query.getStatus())) {
            map.put("status", query.getStatus());
        }
            //会员查询自己的订单
//            private String completion_time;//订单完成时间
            List<OrderVO> list = appOrderMakeSaveDao.listOrders(map);
            for (OrderVO record : list){
//                if(!StringUtil.isNullOrEmpty(record.getCreate_time())){
//                    record.setCreate_time(RelativeDateFormat.format(Long.parseLong(record.getCreate_time())));
//                }
//                if(!StringUtil.isNullOrEmpty(record.getPay_time())){
//                    record.setPay_time(RelativeDateFormat.format(Long.parseLong(record.getPay_time())));
//                }
            }
            return list;
    }


    @Override
    public Object findById(Object object) throws ServiceException {
        String order_no = (String) object;
        OrderVO record = appOrderMakeSaveDao.findOrderByOrderNo(order_no);

//        if(!StringUtil.isNullOrEmpty(record.getCreate_time())){
//            record.setCreate_time(RelativeDateFormat.format(Long.parseLong(record.getCreate_time())));
//        }
//        if(!StringUtil.isNullOrEmpty(record.getPay_time())){
//            record.setPay_time(RelativeDateFormat.format(Long.parseLong(record.getPay_time())));
//        }

        return record;
    }
}
