package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.XixunObjVO;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.RenmaiQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("appEmpIndexService")
public class AppEmpIndexService implements  ListService ,ExecuteService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object list(Object object) throws ServiceException {
        EmpQuery query = (EmpQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getMm_hangye_id())){
            map.put("mm_hangye_id" ,query.getMm_hangye_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyword())){
            map.put("keywords" ,query.getKeyword());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_countryId())){
            map.put("mm_emp_countryId" ,query.getMm_emp_countryId());
        }
        List<EmpVO> list = empDao.listsRenmai(map);
        for (EmpVO record : list){
            if (!StringUtil.isNullOrEmpty(record.getMm_emp_cover())){
                if (record.getMm_emp_cover().startsWith("upload")){
                    record.setMm_emp_cover(Constants.URL+record.getMm_emp_cover());
                }else {
                    record.setMm_emp_cover(Constants.QINIU_URL + record.getMm_emp_cover());
                }
            }
        }
        return  list;
    }

    @Override
    public Object execute(Object object) throws Exception {
        String hxusername = (String) object;
        EmpVO empVO = empDao.findByHxId(hxusername);
        if (empVO.getMm_emp_cover().startsWith("upload")) {
            empVO.setMm_emp_cover(Constants.URL + empVO.getMm_emp_cover());
        }else {
            empVO.setMm_emp_cover(Constants.QINIU_URL + empVO.getMm_emp_cover());
        }
        return empVO;
    }


}
