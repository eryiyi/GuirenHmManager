package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.model.ShoppingTrade;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/14.
 */
@Service("appOrderMakeWxService")
public class AppOrderMakeWxService implements SaveService {
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

        //保存总订单--和微信支付一致
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
        StringBuffer xml = new StringBuffer();
        try {
            final String ip_str = "127.0.0.1";
            final String body = "guiren_yaoqingma";
            final String trade_type = "APP";
            String  nonceStr = UUIDFactory.random();

            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.WX_APP_ID));
            packageParams.add(new BasicNameValuePair("body", body));
            /**这里用的是mach_id,跟sign签名时参数名不同，一定要注意*/
            packageParams.add(new BasicNameValuePair("mch_id", Constants.WX_MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", Constants.WEIXIN_NOTIFY_URL));
            packageParams.add(new BasicNameValuePair("out_trade_no", order.getTrade_no()));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", ip_str));
            packageParams.add(new BasicNameValuePair("total_fee", String.valueOf((int)Math.ceil((Double.valueOf(shoppingTrade.getTrade_prices())*100)))));
            packageParams.add(new BasicNameValuePair("trade_type", trade_type));

            String sign = genAppSign(packageParams).toUpperCase();

            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlstring =toXml(packageParams);

            return new Object[]{xmlstring, order.getTrade_no()};
        } catch (Exception e) {
            return null;
        }
    }


    StringBuffer sb=new StringBuffer();;

    /**
     生成签名
     */
    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.WX_API_KEY);

        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return packageSign;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.WX_API_KEY);

        this.sb.append("sign str\n"+sb.toString()+"\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        return appSign;
    }

    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");


            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

}
