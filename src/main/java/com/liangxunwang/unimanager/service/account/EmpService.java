package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("empService")
public class EmpService implements ListService , UpdateService , ExecuteService{
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

        if (!StringUtil.isNullOrEmpty(query.getKeyword())) {
            map.put("keyword", query.getKeyword());
        }

        //分地区管理
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_provinceId())){
            map.put("mm_emp_provinceId", query.getMm_emp_provinceId());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_cityId())){
            map.put("mm_emp_cityId", query.getMm_emp_cityId());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_countryId())){
            map.put("mm_emp_countryId", query.getMm_emp_countryId());
        }
        if(!StringUtil.isNullOrEmpty(query.getIscheck())){
            map.put("ischeck", query.getIscheck());
        }
        //判断是否今日注册量
//        if(!StringUtil.isNullOrEmpty(query.getMm_emp_regtime())){
//            if("0".equals(query.getMm_emp_regtime())){
//                //不限时间
//                map.put("mm_emp_regtime", "");
//            }else if("1".equals(query.getMm_emp_regtime())){
//                map.put("mm_emp_regtime", DateUtil.getDateAndTimeTwo());
//            }
//        }

        List<EmpVO> lists = empDao.listMemberByName(map);
        long count = empDao.count(map);
        if(lists != null){
           for(EmpVO empVO2 : lists){
               if (empVO2 !=null && !StringUtil.isNullOrEmpty(empVO2.getMm_emp_cover())) {
                   if (empVO2.getMm_emp_cover().startsWith("upload")) {
                       empVO2.setMm_emp_cover(Constants.URL + empVO2.getMm_emp_cover());
                   }else {
                       empVO2.setMm_emp_cover(Constants.QINIU_URL + empVO2.getMm_emp_cover());
                   }
               }
               if (empVO2 !=null && !StringUtil.isNullOrEmpty(empVO2.getMm_emp_bg())) {
                   if (empVO2.getMm_emp_bg().startsWith("upload")) {
                       empVO2.setMm_emp_bg(Constants.URL + empVO2.getMm_emp_bg());
                   }else {
                       empVO2.setMm_emp_bg(Constants.QINIU_URL + empVO2.getMm_emp_bg());
                   }
               }
           }
        }
        return new Object[]{lists, count};
    }

    @Override
    public Object update(Object object) {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            if(emp != null && !StringUtil.isNullOrEmpty(emp.getMm_emp_password())){
                emp.setMm_emp_password(new MD5Util().getMD5ofStr(emp.getMm_emp_password()));//密码加密
                empDao.updatePwr(emp);
            }else {
                if(emp != null && !StringUtil.isNullOrEmpty(emp.getMm_emp_cover()) && !emp.getMm_emp_cover().startsWith("http://")){
                    empDao.updateCover(emp);
                }
                empDao.update(emp);
            }
        }
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        String mm_emp_id = (String) object;
        EmpVO empVO = empDao.findById(mm_emp_id);
        if (empVO !=null && !StringUtil.isNullOrEmpty(empVO.getMm_emp_cover())) {
            if (empVO.getMm_emp_cover().startsWith("upload")) {
                empVO.setMm_emp_cover(Constants.URL + empVO.getMm_emp_cover());
            }else {
                empVO.setMm_emp_cover(Constants.QINIU_URL + empVO.getMm_emp_cover());
            }
        }
        if (empVO !=null && !StringUtil.isNullOrEmpty(empVO.getMm_emp_bg())) {
            if (empVO.getMm_emp_bg().startsWith("upload")) {
                empVO.setMm_emp_bg(Constants.URL + empVO.getMm_emp_bg());
            }else {
                empVO.setMm_emp_bg(Constants.QINIU_URL + empVO.getMm_emp_bg());
            }
        }

        return empVO;
    }

}
