package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.FavourDao;
import com.liangxunwang.unimanager.dao.FuwuDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.Favour;
import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.mvc.vo.FavourVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.FavourQuery;
import com.liangxunwang.unimanager.query.FuwuQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("appFavourService")
public class AppFavourService implements ListService,SaveService ,DeleteService,ExecuteService{
    @Autowired
    @Qualifier("favourDao")
    private FavourDao favourDao;

    @Autowired
    @Qualifier("accessTokenDao")
    private AccessTokenDao accessTokenDao;

    @Override
    public Object list(Object object) throws ServiceException {
        FavourQuery query = (FavourQuery) object;

        Map<String, Object> map = new HashMap<String, Object>();

        //判断accesstoken是否存在 是否是最新的
        if(!StringUtil.isNullOrEmpty(query.getAccessToken())){
            //不为空，判断是否单点登录
            AccessToken accessToken = accessTokenDao.findByToken(query.getAccessToken());
            if(accessToken == null){
                throw new ServiceException("accessTokenNull");
            }
        }else {
            throw new ServiceException("accessTokenNull");
        }

        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id", query.getMm_emp_id());
        }

        List<FavourVO> lists = favourDao.lists(map);
        for (FavourVO record : lists){
            if (!StringUtil.isNullOrEmpty(record.getMm_emp_cover())){
                if (record.getMm_emp_cover().startsWith("upload")){
                    record.setMm_emp_cover(Constants.URL+record.getMm_emp_cover());
                }else {
                    record.setMm_emp_cover(Constants.QINIU_URL + record.getMm_emp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getMm_msg_picurl())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(record!=null && record.getMm_msg_picurl()!=null){
                    pics = record.getMm_msg_picurl().split(",");
                }
                for (int i=0; i<pics.length; i++){
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                record.setMm_msg_picurl(buffer.toString());
            }
            if(!StringUtil.isNullOrEmpty(record.getDatelineRecord())){
//                record.setDatelineRecord(RelativeDateFormat.format(Long.parseLong(record.getDatelineRecord())));
                record.setDatelineRecord(DateUtil.getDate(record.getDatelineRecord(), "yyyy-MM-dd HH:mm"));
            }

        }
        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        Favour favour = (Favour) object;
        Map<String, Object> map = new HashMap<String, Object>();
        //判断accesstoken是否存在 是否是最新的
        if(!StringUtil.isNullOrEmpty(favour.getAccessToken())){
            //不为空，判断是否单点登录
            AccessToken accessToken = accessTokenDao.findByToken(favour.getAccessToken());
            if(accessToken == null){
                throw new ServiceException("accessTokenNull");
            }
        }else {
            throw new ServiceException("accessTokenNull");
        }

        favour.setMm_record_favour_id(UUIDFactory.random());
        favour.setDateline(DateUtil.getDateAndTime());//时间戳
        map.put("mm_msg_id", favour.getMm_msg_id());
        map.put("mm_emp_id", favour.getMm_emp_id());
        Favour favour1 =  favourDao.findOne(map);
        if(favour1 != null){
            //已经收藏了
            throw new ServiceException("hasExist");
        }
        favourDao.save(favour);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_record_favour_id = (String) object;
        favourDao.delete(mm_record_favour_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return favourDao.findById((String) object);
    }

}
