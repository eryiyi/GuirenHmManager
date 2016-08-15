package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.dao.YaoqingCardDao;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.YaoqingCard;
import com.liangxunwang.unimanager.mvc.vo.YaoqingCardVO;
import com.liangxunwang.unimanager.query.FuwuQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
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
@Service("yaoqingCardService")
public class YaoqingCardService implements ListService,SaveService ,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("yaoqingCardDao")
    private YaoqingCardDao yaoqingCardDao;

    @Override
    public Object list(Object object) throws ServiceException {
        YaoqingCardQuery query = (YaoqingCardQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getGuiren_card_id())){
            map.put("guiren_card_id",query.getGuiren_card_id() );
        }
        if(!StringUtil.isNullOrEmpty(query.getGuiren_card_num())){
            map.put("guiren_card_num",query.getGuiren_card_num() );
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use",query.getIs_use() );
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id",query.getMm_emp_id() );
        }
        if(!StringUtil.isNullOrEmpty(query.getY_mm_emp_id())){
            map.put("y_mm_emp_id",query.getY_mm_emp_id() );
        }
         List<YaoqingCardVO> lists = yaoqingCardDao.list(map);
        long count = yaoqingCardDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        YaoqingCardQuery yaoqingCardQuery = (YaoqingCardQuery) object;
        int numCard = Integer.parseInt(yaoqingCardQuery.getAdd_one());
        for(int i=0;i<numCard;i++){
            YaoqingCard yaoqingCard = new YaoqingCard();
            if(!StringUtil.isNullOrEmpty(yaoqingCardQuery.getMm_emp_id())){
                yaoqingCard.setMm_emp_id(yaoqingCardQuery.getMm_emp_id());//邀请人
            }else {
                yaoqingCard.setMm_emp_id("0");//如果是后台生成的邀请码  直接写0
            }
            yaoqingCard.setGuiren_card_id(UUIDFactory.random());
            yaoqingCard.setIs_use("0");
            yaoqingCard.setGuiren_card_num(StringUtil.getStringRandom(8));
            yaoqingCard.setCreatetime(System.currentTimeMillis() + "");
            yaoqingCardDao.save(yaoqingCard);
        }
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        YaoqingCardQuery query = (YaoqingCardQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getGuiren_card_id())){
            map.put("guiren_card_id",query.getGuiren_card_id() );
        }
        if(!StringUtil.isNullOrEmpty(query.getGuiren_card_num())){
            map.put("guiren_card_num",query.getGuiren_card_num() );
        }
        return yaoqingCardDao.findById(map);
    }

    @Override
    public Object update(Object object) {
        YaoqingCard level = (YaoqingCard) object;
        yaoqingCardDao.update(level);
        return null;
    }
}
