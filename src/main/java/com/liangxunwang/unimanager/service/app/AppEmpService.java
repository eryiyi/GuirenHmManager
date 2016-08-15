package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpAdVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("appEmpService")
public class AppEmpService implements  UpdateService,ListService ,ExecuteService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object execute(Object object) throws Exception {
        String mobile = (String) object;
        EmpVO empVO = empDao.findByMobile(mobile);
        return empVO;
    }

    //修改会员信息
    @Override
    public Object update(Object object) throws ServiceException {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            if(!StringUtil.isNullOrEmpty(emp.getMm_emp_password()))
            {
                //修改密码
                //1，先查看身份证号和手机号是否正确
                Map<String, Object> map = new HashMap<String, Object>();
                if(!StringUtil.isNullOrEmpty(emp.getMm_emp_id())){
                    map.put("mm_emp_id", emp.getMm_emp_id());
                }
                if(!StringUtil.isNullOrEmpty(emp.getMm_emp_card())){
                    map.put("mm_emp_card", emp.getMm_emp_card());
                }
                EmpVO empVO = empDao.findByMobileAndCard(map);
                if(empVO != null){
                    //说明手机号和身份证号正确
                    emp.setMm_emp_password(new MD5Util().getMD5ofStr(emp.getMm_emp_password()));//密码加密
                    empDao.updatePwr(emp);
                }else {
                    throw new ServiceException("has_none");
                }

            }else {
                //上传用户定位数据  经纬度
                empDao.updateLoacation(emp);
            }
        }
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
//        EmpQuery query = (EmpQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        List<EmpVO> lists = empDao.listsLocation(map);
        List<EmpVO> listsTmp = new ArrayList<EmpVO>();
        for(EmpVO empVO:lists){
            if(!StringUtil.isNullOrEmpty(empVO.getLat())){
                if (!StringUtil.isNullOrEmpty(empVO.getMm_emp_cover())) {
                        if (empVO.getMm_emp_cover().startsWith("upload")) {
                            empVO.setMm_emp_cover(Constants.URL + empVO.getMm_emp_cover());
                        }else {
                            empVO.setMm_emp_cover(Constants.QINIU_URL + empVO.getMm_emp_cover());
                        }
                }
                listsTmp.add(empVO);
            }
        }
        return listsTmp;
    }

}
