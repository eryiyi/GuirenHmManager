package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/1/29.
 */
@Service("memberLoginService")
public class MemberLoginService implements ExecuteService {
    @Autowired
    @Qualifier("empDao")
    private EmpDao dao;

    @Override
    public Object execute(Object object) {
        Object[] params = (Object[]) object;
        String username = (String) params[0];
        String password = (String) params[1];
        EmpVO member = dao.findByMobile(username);
        if (member == null){
            throw new ServiceException("NotFound");
        }
        if (!new MD5Util().getMD5ofStr(password).equals(member.getMm_emp_password())){
            throw new ServiceException("PassError");
        }
        if ("1".equals(member.getIs_use())){
            throw new ServiceException("NotUse");//禁用
        }
        if (!"1".equals(member.getIscheck())){
            throw new ServiceException("NotCheck");//未审核
        }

        if(!StringUtil.isNullOrEmpty(member.getMm_emp_cover())){
            if (member.getMm_emp_cover().startsWith("upload")) {
                member.setMm_emp_cover(Constants.URL + member.getMm_emp_cover());
            }else {
                member.setMm_emp_cover(Constants.QINIU_URL + member.getMm_emp_cover());
            }

        }
        if(!StringUtil.isNullOrEmpty(member.getMm_emp_bg())){
            if (member.getMm_emp_bg().startsWith("upload")) {
                member.setMm_emp_bg(Constants.URL + member.getMm_emp_bg());
            }else {
                member.setMm_emp_bg(Constants.QINIU_URL + member.getMm_emp_bg());
            }
        }

        return member;
    }
}
