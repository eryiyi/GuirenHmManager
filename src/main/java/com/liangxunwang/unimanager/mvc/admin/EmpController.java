package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.*;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/emp")
public class EmpController extends ControllerConstants {
    @Autowired
    @Qualifier("empService")
    private ListService empServiceList;

    @Autowired
    @Qualifier("empService")
    private UpdateService empServiceUpdate;

    @Autowired
    @Qualifier("levelService")
    private ListService levelService;

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;
    @Autowired
    @Qualifier("countryService")
    private ListService countryService;


    @Autowired
    @Qualifier("empService")
    private ExecuteService empServiceExecute;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, EmpQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) empServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //日志记录
        logoService.save(new LogoObj("查看会员列表", manager.getMm_manager_id()));
        return "/emp/list";
    }

    @RequestMapping("/detail")
    public String updateType(ModelMap map, String mm_emp_id) throws Exception {
        //查看该会员信息
        EmpVO empVO = (EmpVO) empServiceExecute.execute(mm_emp_id);
        map.put("empVO", empVO);
        return "/emp/detail";
    }

    //更改会员数据
    @RequestMapping("/updateEmp")
    @ResponseBody
    public String updateEmp( HttpSession session, Emp emp){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            empServiceUpdate.update(emp);
            //日志记录
            logoService.save(new LogoObj("更新会员:："+emp.getMm_emp_mobile()+"的信息", manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //管理员--添加管理员-搜索会员
    @RequestMapping("listAddManager")
    public String listAddManager(HttpSession session,ModelMap map, EmpQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) empServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //查询等级
        LevelQuery levelQuery = new LevelQuery();
        List<Level> list = (List<Level>) levelService.list(levelQuery);
        map.put("listLevels", list);

        //日志记录
        logoService.save(new LogoObj("添加管理员-查看会员信息", manager.getMm_manager_id()));
        return "/admin/add_list";
    }


    @Autowired
    @Qualifier("roleService")
    private ListService roleService;

    //管理员--添加管理员-搜索会员详情
    @RequestMapping("/listAddManager/detail")
    public String listAddManagerDetail(ModelMap map, HttpSession session, String mm_emp_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        //查看该会员信息
        EmpVO empVO = (EmpVO) empServiceExecute.execute(mm_emp_id);

        //角色
        List<Role> roles = (List<Role>) roleService.list("");
        map.put("roles", roles);
        //日志记录
        logoService.save(new LogoObj("查看会员："+empVO.getMm_emp_nickname()+"的详情", manager.getMm_manager_id()));
        return "/admin/add_detail";
    }

    @Autowired
    @Qualifier("empRegisterService")
    private SaveService empRegisterService;

    /**
     * 注册功能
     * @param member  会员对象
     * @return
     */
    @RequestMapping("/empReg")
    @ResponseBody
    public String empReg(Emp member){
        try {
            empRegisterService.save(member);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("MobileIsUse")){
                return toJSONString(ERROR_2);
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_1);
            }

        }
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toReg")
    public String toReg(ModelMap map, HttpSession session, String mm_emp_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);

        //查询省份
        ProvinceQuery provinceQuery = new ProvinceQuery();
        provinceQuery.setIs_use("1");
        List<ProvinceObj> listProvinces = (List<ProvinceObj>) provinceService.list(provinceQuery);
        //查询地市
        CityQuery cityQuery = new CityQuery();
        cityQuery.setIs_use("1");
        List<CityObj> listCitys = (List<CityObj>) cityService.list(cityQuery);
        //查询县区
        CountryQuery countryQuery = new CountryQuery();
        countryQuery.setIs_use("1");
        List<CountryObj> listsCountry = (List<CountryObj>) countryService.list(countryQuery);
        map.put("listProvinces", listProvinces);
        map.put("listCitys", listCitys);
        map.put("listsCountry", listsCountry);

        //查询地市all
        CityQuery cityQueryAll = new CityQuery();
        List<CityObj> listCitysAll = (List<CityObj>) cityService.list(cityQueryAll);
        //查询县区all
        CountryQuery countryQueryAll = new CountryQuery();
        List<CountryObj> listsCountryAll = (List<CountryObj>) countryService.list(countryQueryAll);

        map.put("listCitysAll", toJSONString(listCitysAll));
        map.put("listsCountryAll", toJSONString(listsCountryAll));

        return "/emp/addEmp";
    }


    @RequestMapping("/toUpdatePwr")
    public String toUpdatePwr(ModelMap map, HttpSession session, String mm_emp_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        //查看该会员信息
        EmpVO empVO = (EmpVO) empServiceExecute.execute(mm_emp_id);
        map.put("empVO", empVO);
        return "/emp/updatePwr";
    }


    //更改会员数据--密码
    @RequestMapping("/updateEmpPwr")
    @ResponseBody
    public String updateEmpPwr( HttpSession session, Emp emp){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            empServiceUpdate.update(emp);
            //日志记录
            logoService.save(new LogoObj("更新会员密码："+emp.getMm_emp_nickname()+"的信息", manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("empExcelService")
    private ExecuteService empExcelService;

    //导出Excel表格数据
    @RequestMapping("daochuAll")
    @ResponseBody
    public String daochuAll(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) empExcelService.execute(objects);

            //日志记录
            logoService.save(new LogoObj("导出excel用户表", manager.getMm_manager_id()));

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }


    @Autowired
    @Qualifier("empPLoginService")
    private ExecuteService empPLoginService;
    //批量处理用户--允许登陆0 不允许登陆1
    @RequestMapping("pLoginAction")
    @ResponseBody
    public String pLoginAction(HttpSession session,String ids,String type) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objectses = new Object[2];
            objectses[0]=ids;
            objectses[1]=type;
            empPLoginService.execute(objectses);
            //日志记录
            logoService.save(new LogoObj("批量处理用户数据_登陆权限："+ids, manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }

    //-------------------每天凌晨执行，查询是否有过期的vip会员--------------------------
    public String update(){
        updateEmpVipGuoqi();
        return null;
    }

    @Autowired
    @Qualifier("empUpdateVipService")
    private UpdateService empUpdateVipService;

    @RequestMapping("/updateEmpVipGuoqi")
    @ResponseBody
    public String updateEmpVipGuoqi(){
        try {
            empUpdateVipService.update("");
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    //-------------------------------------------------

    @Autowired
    @Qualifier("empPDeleteEmpService")
    private ExecuteService empPDeleteEmpService;
    //批量删除用户
    @RequestMapping("pDeleteEmpAction")
    @ResponseBody
    public String pDeleteEmpAction(HttpSession session,String ids) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            empPDeleteEmpService.execute(ids);
            //日志记录
            logoService.save(new LogoObj("批量删除用户："+ids, manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }

}
