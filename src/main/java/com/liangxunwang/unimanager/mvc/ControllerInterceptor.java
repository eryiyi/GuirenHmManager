package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 */
public class ControllerInterceptor extends ControllerConstants implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        Object account = request.getSession().getAttribute(ACCOUNT_KEY);

        if(uri.contains(".json")){
                return true;
        }

        if(uri.matches("(^/$)|(^/index\\.do$)|(^/adminLogin\\.do$)|(^/logout\\.do$)" +
                        "|(^/uploadImage\\.do$)" +
                        "|(^/uploadFileXls\\.do$)" +
                        "|(^/uploadUnCompressImage\\.do$)" +
                        "|(^/getMemberInfoById\\.do$)"+
                        "|(^/getMemberByMobile\\.do$)"+
                        "|(^/getCity\\.do$)"+
                        "|(^/getCountry\\.do$)"+
                        "|(^/getProvince\\.do$)"+
                        "|(^/memberRegister\\.do$)"+
                        "|(^/recordList\\.do$)"+
                        "|(^/sendRecord\\.do$)"+
                        "|(^/getRecordById\\.do$)"+
                        "|(^/viewRecord\\.do$)"+
                        "|(^/recordListById\\.do$)"+
                        "|(^/sendLocation\\.do$)"+
                        "|(^/getFuwuByLocationAndType\\.do$)"+
                        "|(^/getAboutUs\\.do$)"+
                        "|(^/suggest/addSuggest\\.do$)"+
                        "|(^/updatePwr\\.do$)"+
                        "|(^/updatePwrApp\\.do$)"+
                        "|(^/getEmpAd\\.do$)"+
                        "|(^/getNearby\\.do$)"+
                        "|(^/getTopMsg\\.do$)"+
                        "|(^/getVipList\\.do$)"+
                        "|(^/getLevelById\\.do$)"+
                        "|(^/saveReport\\.do$)"+
                        "|(^/getNearbyDistance\\.do$)"+
                        "|(^/saveFavour\\.do$)"+
                        "|(^/getFavourById\\.do$)"+
                        "|(^/getKefuTel\\.do$)"+
                        "|(^/updatePushId\\.do$)"+
                        "|(^/getFavourCount\\.do$)"+
                        "|(^/deleteFavour\\.do$)"+
                        "|(^/deleteFavourGoods\\.do$)"+
                        "|(^/getLoginAd\\.do$)"+
                        "|(^/memberUpdateProfile\\.do$)"+
                        "|(^/getKefuWeixin\\.do$)"+
                        "|(^/getPaihangs\\.do$)"+
                        "|(^/saveGuanzhuArea\\.do$)"+
                        "|(^/getGuanzhuArea\\.do$)"+
                        "|(^/getNotices\\.do$)"+
                        "|(^/getNoticesDetail\\.do$)"+
                        "|(^/updateCover\\.do$)"+
                        "|(^/deleteRecordById\\.do$)"+
                        "|(^/zanRecord\\.do$)"+
                        "|(^/listZan\\.do$)"+
                        "|(^/saveComment\\.do$)"+
                        "|(^/getCommentsByRecord\\.do$)"+
                        "|(^/getHyTypes\\.do$)"+
                        "|(^/getYaoqingCardEmp\\.do$)"+
                        "|(^/orderSave\\.do$)"+
                        "|(^/orderUpdate\\.do$)"+
                        "|(^/listOrders\\.do$)"+
                        "|(^/updateOrder\\.do$)"+
                        "|(^/orderSaveSingle\\.do$)"+
                        "|(^/getEmps\\.do$)"+
                        "|(^/xixunList\\.do$)"+
                        "|(^/listRenmaidu\\.do$)"+
                        "|(^/updateMobile\\.do$)"+
                        "|(^/editEmpById\\.do$)"+
                        "|(^/getHxByName\\.do$)"+
                        "|(^/listInviteMemberInfo\\.do$)"+
                        "|(^/getEmpRelates\\.do$)"+
                        "|(^/saveEmpRelate\\.do$)"+
                        "|(^/updateEmpRelate\\.do$)"+
                        "|(^/listRelate\\.do$)"+
                        "|(^/updateEmpCheck\\.do$)"+
                        "|(^/managerinfo/save\\.do$)"+


                        "|(^/deleteDianpuFavour\\.do$)"+
                        "|(^/saveDianpuFavour\\.do$)"+
                        "|(^/saveDianpu\\.do$)"+
                        "|(^/appGetDianpuFavour\\.do$)"+
                        "|(^/listGoodsByType\\.do$)"+
                        "|(^/addGoods\\.do$)"+
                        "|(^/deleteGoodsById\\.do$)"+
                        "|(^/findGoodsById\\.do$)"+
                        "|(^/listGoodsComment\\.do$)"+
                        "|(^/saveGoodsComment\\.do$)"+
                        "|(^/getMemberInfoById\\.do$)"+
                        "|(^/viewGoods\\.do$)"
                        +"|(^/saveGoodsFavour\\.do$)"
                        +"|(^/listFavour\\.do$)"
                        +"|(^/deleteFavour\\.do$)"
                        +"|(^/saveShoppingAddress\\.do$)"
                        +"|(^/listShoppingAddress\\.do$)"
                        +"|(^/deleteShoppingAddress\\.do$)"
                        +"|(^/updateShoppingAddress\\.do$)"
                        +"|(^/getSingleAddressByEmpId\\.do$)"
                        +"|(^/orderSave\\.do$)"
                        +"|(^/orderUpdate\\.do$)"
                        +"|(^/listOrders\\.do$)"
                        +"|(^/getSingleAddressByAddressId\\.do$)"
                        +"|(^/updateOrder\\.do$)"
                        +"|(^/orderSaveSingle\\.do$)"
                        +"|(^/orderUpdateSingle\\.do$)"
                        +"|(^/appGetProvince\\.do$)"
                        +"|(^/appGetCity\\.do$)"
                        +"|(^/appGetArea\\.do$)"
                        +"|(^/paopaogoods/listGoods\\.do$)"
                        +"|(^/paopaogoods/findById\\.do$)"
                        +"|(^/paopaogoods/detail\\.do$)"
                        +"|(^/viewpager/appList\\.do$)"
                        +"|(^/selectOrderNum\\.do$)"
                        +"|(^/listOrdersMng\\.do$)"
                        +"|(^/cancelOrderSave\\.do$)"
                        +"|(^/paopaogoods/updatePosition\\.do$)"
                        +"|(^/findOrderByOrderNo\\.do$)"
                        +"|(^/paopaogoods/delete\\.do$)"
                        +"|(^/viewNewsShare\\.do$)"
                        +"|(^/listVideosApp\\.do$)"
                        +"|(^/listVideosComment\\.do$)"
                        +"|(^/appVideosSaveComment\\.do$)"
                        +"|(^/appVideosListZan\\.do$)"
                        +"|(^/appVideoZanSave\\.do$)"
                        +"|(^/viewVideos\\.do$)"
                        +"|(^/listsRecodMoods\\.do$)"
                        +"|(^/appGetDianPu\\.do$)"
                        +"|(^/appGetAds\\.do$)"
                        +"|(^/appGetThreesBd\\.do$)"
                        +"|(^/appGetProfileMsg\\.do$)"
                        +"|(^/updateMineBg\\.do$)"
                        +"|(^/listFindsApp\\.do$)"
                        +"|(^/paopaogoods/updatePaopaoGoodsJia\\.do$)"+

                        "|(^/orderSave\\.do$)"+
                        "|(^/orderUpdate\\.do$)"+
                        "|(^/orderSaveWx\\.do$)"+
                        "|(^/orderSaveWxFk\\.do$)"+

                        "|(^/getMoneyJiage\\.do$)"+
                        "|(^/getVersionCode\\.do$)"+
                        "|(^/recordPics\\.do$)"+
                        "|(^/updateRelateById\\.do$)"+

                        "|(^/getVideoTypes\\.do$)"+
                        "|(^/getDianyingTypes\\.do$)"+
                        "|(^/listVideosAppTv\\.do$)"+
                        "|(^/appVideoZanSaveTV\\.do$)"+
                        "|(^/appVideosListZanTV\\.do$)"+
                        "|(^/listVideosCommentTv\\.do$)"+
                        "|(^/appVideosSaveCommentTv\\.do$)"+
                        "|(^/viewVideosTv\\.do$)"+
                        "|(^/getGdTypes\\.do$)"+
                        "|(^/appGetHelps\\.do$)"+
                        "|(^/appSaveHelp\\.do$)"+
                        "|(^/getHelpTypes\\.do$)"+
                        "|(^/getHelpDanwei\\.do$)"+

                        "|(^/memberLogin\\.do$)"

        ) || account != null) {
            return true;
        }

        if("true".equals(request.getParameter("j"))) {
            PrintWriter out = response.getWriter();
            out.print(toJSONString(TIMEOUT));
            out.close();
        } else {
            request.getRequestDispatcher("/WEB-INF/session.jsp").forward(request, response);
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
