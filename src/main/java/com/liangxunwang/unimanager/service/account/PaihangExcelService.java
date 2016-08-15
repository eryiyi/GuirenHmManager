package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.CreateSimpleExcelToDisk;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Service("paihangExcelService")
public class PaihangExcelService implements ExecuteService{
    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            //查询这些用户的数据
            List<PaihangObjVO> empVOs = new ArrayList<PaihangObjVO>();
            for(int i=0;i<arrs.length;i++){
                PaihangObjVO empVO = paihangObjDao.findById(arrs[i]);
                if(empVO != null && !StringUtil.isNullOrEmpty(empVO.getMm_emp_id())){
                    if(empVO != null && !StringUtil.isNullOrEmpty(empVO.getEnd_time())){
                        //vip到期日期不为空
                        empVO.setEnd_time(DateUtil.getDate(empVO.getEnd_time(), "yyyy-MM-dd"));
                    }
                    empVOs.add(empVO);
                }
            }
            String fileName = CreateSimpleExcelToDisk.toExcelPaihang(empVOs, request);
            return fileName;
        }
        return null;
    }




}
