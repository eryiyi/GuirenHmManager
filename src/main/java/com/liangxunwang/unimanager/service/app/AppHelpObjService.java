package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.HelpObjDao;
import com.liangxunwang.unimanager.dao.HelpTypeDao;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.HelpObj;
import com.liangxunwang.unimanager.model.HelpType;
import com.liangxunwang.unimanager.query.HelpObjQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
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
 */
@Service("appHelpObjService")
public class AppHelpObjService implements ListService,SaveService{

    @Autowired
    @Qualifier("helpObjDao")
    private HelpObjDao helpObjDao;


    @Autowired
    @Qualifier("helpTypeDao")
    private HelpTypeDao helpTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        HelpObjQuery query = (HelpObjQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getHelp_danwei_id())){
            map.put("help_danwei_id", query.getHelp_danwei_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getHelp_type_id())){
            Map<String, Object> mapType = new HashMap<String, Object>();
            mapType.put("help_type_f_id", query.getHelp_type_id());
            List<HelpType> listTypes= helpTypeDao.lists(mapType);
            if(listTypes != null && listTypes.size() > 0){
                String[] schoolIds = new String[listTypes.size()];
                for (int i = 0; i < listTypes.size(); i++) {
                    schoolIds[i] = listTypes.get(i).getHelp_type_id();
                }
                map.put("schoolIds", schoolIds);
            }
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id", query.getMm_emp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeywords())){
            map.put("keywords", query.getKeywords());
        }
        if(!StringUtil.isNullOrEmpty(query.getHelp_type())){
            map.put("help_type", query.getHelp_type());
        }
        if(!StringUtil.isNullOrEmpty(query.getCityID())){
            map.put("cityID", query.getCityID());
        }

        List<HelpObj> lists = helpObjDao.lists(map);

        if(lists != null){
            for(HelpObj helpObj:lists){

                if (!StringUtil.isNullOrEmpty(helpObj.getMm_emp_cover())) {
                    if (helpObj.getMm_emp_cover().startsWith("upload")) {
                        helpObj.setMm_emp_cover(Constants.URL + helpObj.getMm_emp_cover());
                    }else {
                        helpObj.setMm_emp_cover(Constants.QINIU_URL + helpObj.getMm_emp_cover());
                    }
                }

                if(!StringUtil.isNullOrEmpty(helpObj.getHelp_pic())){
                    //处理图片URL链接
                    StringBuffer buffer = new StringBuffer();
                    String[] pics = new String[]{};
                    if(helpObj!=null && helpObj.getHelp_pic()!=null){
                        pics = helpObj.getHelp_pic().split(",");
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
                    helpObj.setHelp_pic(buffer.toString());
                }

            }
        }

        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        HelpObj helpObj = (HelpObj) object;
        helpObj.setHelp_id(UUIDFactory.random());
        helpObj.setDateline(System.currentTimeMillis()+"");
        helpObjDao.save(helpObj);
        return null;
    }


}
