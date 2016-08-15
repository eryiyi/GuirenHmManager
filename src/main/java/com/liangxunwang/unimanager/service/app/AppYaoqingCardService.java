package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.YaoqingCardDao;
import com.liangxunwang.unimanager.model.YaoqingCard;
import com.liangxunwang.unimanager.mvc.vo.YaoqingCardVO;
import com.liangxunwang.unimanager.query.YaoqingCardQuery;
import com.liangxunwang.unimanager.service.*;
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
@Service("appYaoqingCardService")
public class AppYaoqingCardService implements ListService,ExecuteService{
    @Autowired
    @Qualifier("yaoqingCardDao")
    private YaoqingCardDao yaoqingCardDao;

    @Override
    public Object list(Object object) throws ServiceException {
        YaoqingCardQuery query = (YaoqingCardQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id",query.getMm_emp_id() );
        }
         List<YaoqingCardVO> lists = yaoqingCardDao.findByEmp(map);
        return lists;
    }

    @Override
    public Object execute(Object object) throws Exception {
        String id = (String) object;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("guiren_card_id", id);
        YaoqingCard  yaoqingCard = yaoqingCardDao.findById(map);
        return yaoqingCard;
    }
}
