package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("appAdObjService")
public class AppAdObjService implements ListService{
    @Autowired
    @Qualifier("adDao")
    private AdDao leveldDao;

    @Override
    public Object list(Object object) throws ServiceException {
        AdQuery query = (AdQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_ad_type())){
            map.put("mm_ad_type", query.getMm_ad_type());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_provinceId())){
            map.put("mm_emp_provinceId", query.getMm_emp_provinceId());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_cityId())){
            map.put("mm_emp_cityId", query.getMm_emp_cityId());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_countryId())){
            map.put("mm_emp_countryId", query.getMm_emp_countryId());
        }
        List<AdObj> lists = leveldDao.lists(map);
        if(lists == null || lists.size()==0){
            Map<String, Object> map1 = new HashMap<String, Object>();
            if(!StringUtil.isNullOrEmpty(query.getMm_ad_type())){
                map1.put("mm_ad_type", query.getMm_ad_type());
            }
            lists = leveldDao.lists(map1);
        }
        if(lists != null){
            for(AdObj adObj:lists){
                if(!StringUtil.isNullOrEmpty(adObj.getMm_ad_pic())){
                    if(adObj.getMm_ad_pic().startsWith("upload")){
                        adObj.setMm_ad_pic(Constants.URL + adObj.getMm_ad_pic());
                    }else {
                        adObj.setMm_ad_pic(Constants.QINIU_URL + adObj.getMm_ad_pic());
                    }
                }
            }
        }
        return lists;
    }

}
