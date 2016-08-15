package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.GuanzhuAreaDao;
import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.GuanzhuAreaObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.GuanzhuAreaObjVO;
import com.liangxunwang.unimanager.query.GuanzhuAreaQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.DateUtil;
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
@Service("guanzhuAreaService")
public class GuanzhuAreaService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("guanzhuAreaDao")
    private GuanzhuAreaDao guanzhuAreaDao;

    @Autowired
    @Qualifier("accessTokenDao")
    private AccessTokenDao accessTokenDao;

    @Override
    public Object list(Object object) throws ServiceException {
        GuanzhuAreaQuery query = (GuanzhuAreaQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id", query.getMm_emp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIscheck())){
            map.put("ischeck", query.getIscheck());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyword())){
            map.put("keyword", query.getKeyword());
        }
        List<GuanzhuAreaObjVO> lists = guanzhuAreaDao.lists(map);
        long count = guanzhuAreaDao.count(map);

        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        GuanzhuAreaObj guanzhuAreaObj = (GuanzhuAreaObj) object;
        //判断accesstoken是否存在 是否是最新的
        if(!StringUtil.isNullOrEmpty(guanzhuAreaObj.getAccessToken())){
            //不为空，判断是否单点登录
            AccessToken accessToken = accessTokenDao.findByToken(guanzhuAreaObj.getAccessToken());
            if(accessToken == null){
                throw new ServiceException("accessTokenNull");
            }
        }
        //判断是否已经存在了
        GuanzhuAreaObjVO guanzhuAreaObjVO = guanzhuAreaDao.findByEmpId(guanzhuAreaObj.getMm_emp_id());
        if(guanzhuAreaObjVO != null && !StringUtil.isNullOrEmpty(guanzhuAreaObjVO.getMm_guanzhu_id() )){
            throw new ServiceException("has_exist");
        }else {
            guanzhuAreaObj.setMm_guanzhu_id(UUIDFactory.random());
            guanzhuAreaObj.setReg_time(DateUtil.getDateAndTime());
            guanzhuAreaDao.save(guanzhuAreaObj);
        }
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_guanzhu_id = (String) object;
        guanzhuAreaDao.delete(mm_guanzhu_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return guanzhuAreaDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        GuanzhuAreaObj guanzhuAreaObj = (GuanzhuAreaObj) object;
        guanzhuAreaDao.update(guanzhuAreaObj);
        return null;
    }
}
