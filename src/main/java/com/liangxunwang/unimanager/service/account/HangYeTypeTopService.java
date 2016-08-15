package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.HangYeTypeDao;
import com.liangxunwang.unimanager.model.HangYeType;
import com.liangxunwang.unimanager.mvc.vo.HangYeTypeVO;
import com.liangxunwang.unimanager.query.HangYeTypeQuery;
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
@Service("hangYeTypeTopService")
public class HangYeTypeTopService implements ListService{
    @Autowired
    @Qualifier("hangYeTypeDao")
    private HangYeTypeDao hangYeTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        HangYeTypeQuery query = (HangYeTypeQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        List<HangYeTypeVO> lists = hangYeTypeDao.listsTop(map);
        return lists;
    }



}
