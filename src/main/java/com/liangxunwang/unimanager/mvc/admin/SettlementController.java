package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.model.Settlement;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.SettlementQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liuzh on 2015/8/24.
 * 与结算相关的接口
 */
@RequestMapping("/settlement")
public class SettlementController extends ControllerConstants {

    @Autowired
    @Qualifier("orderService")
    private ListService orderListService;



    @RequestMapping("list")
    public String list(SettlementQuery query, HttpSession session , ModelMap map){

        return "/settlement/list";
    }

    /**
     * 结算管理时查看所有的订单详情，已完成的
     * @param query
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("orders")
    public String orders(SettlementQuery query, HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);

        List<OrdersVO> list = (List<OrdersVO>) orderListService.list(query);
        map.put("list", list);
        map.put("query", query);
        return "/settlement/orders";
    }

    /**
     * 查看商家的结算账号信息并进行结算
     * @param query
     * @param map
     * @return
     */
    @RequestMapping("endOrder")
    public String endOrder(SettlementQuery query, ModelMap map){

        return "/settlement/endorder";
    }



    @RequestMapping("sellerlist")
    public String sellerlist(SettlementQuery query, ModelMap map, Page page){

        return "/settlement/sellers";
    }

    @RequestMapping("settlementEnd")
    @ResponseBody
    public String settlement(SettlementQuery query){
        return toJSONString(SUCCESS);
    }
}
