package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.VideoTypeDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.VideoTypeObj;
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
@Service("videoTypeObjService")
public class VideoTypeObjService implements ListService,SaveService ,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("videoTypeDao")
    private VideoTypeDao videoTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<VideoTypeObj> lists = videoTypeDao.lists(map);
        if(lists != null){
            for(VideoTypeObj adObj:lists){
                if(!StringUtil.isNullOrEmpty(adObj.getVideo_type_pic())){
                    if(adObj.getVideo_type_pic().startsWith("upload")){
                        adObj.setVideo_type_pic(Constants.URL + adObj.getVideo_type_pic());
                    }else {
                        adObj.setVideo_type_pic(Constants.QINIU_URL + adObj.getVideo_type_pic());
                    }
                }
            }
        }
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        VideoTypeObj videoTypeObj = (VideoTypeObj) object;
        videoTypeObj.setVideo_type_id(UUIDFactory.random());
        videoTypeDao.save(videoTypeObj);
        return null;
    }


    @Override
    public Object execute(Object object) throws ServiceException {
        return videoTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        VideoTypeObj videoTypeObj = (VideoTypeObj) object;
        videoTypeDao.update(videoTypeObj);
        return null;
    }
}
