package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.EmpRelateDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpRelateObjVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.RenmaiQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 */
@Service("appEmpRenmaiService")
public class AppEmpRenmaiService implements  ListService {
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Autowired
    @Qualifier("empRelateDao")
    private EmpRelateDao empRelateDao;

    @Override
    public Object list(Object object) throws ServiceException {
        RenmaiQuery query = (RenmaiQuery) object;
        //处理逻辑
        //先查询两个用户的信息
        EmpVO empVO = empDao.findById(query.getEmp_id());
        EmpVO empVO1 = empDao.findById(query.getEmp_id_t());

        //获得两个用户的标志
        String top_number_one = empVO.getTop_number();
        String top_number_two = empVO1.getTop_number();
        List<EmpVO> lists = new ArrayList<EmpVO>();//推荐路线
        List<EmpVO> listsRenxing = new ArrayList<EmpVO>();//任性路线
        List<EmpVO> listsDuan = new ArrayList<EmpVO>();//最短路线
        if(top_number_one != null && top_number_two !=null){
            if(top_number_one.substring(0,4).equals(top_number_two.substring(0,4))){
                //如果顶级父类相同
                Map<String, Object> mapTopNumber = new HashMap<String, Object>();
                mapTopNumber.put("top_number", top_number_one.substring(0,4));
                List<EmpVO>  lists2 = empDao.listtopnumber3(mapTopNumber);
                if(top_number_one.contains(top_number_two) || top_number_two.contains(top_number_one)){
                    //他俩有上下级关系
                    List<String> listStr = new ArrayList<String>();//存放他们的number标志 从B到A的
                    if(top_number_one.length() > top_number_two.length()){
                        //top_number_two是父  top_number_one是子
                        String[] arrStr = StringUtil.arrStr(top_number_one);
                        for(int i=0;i<arrStr.length;i++){
                            listStr.add(arrStr[i]);
                            if(arrStr[i].equals(top_number_two)){
                                //找到了
                                break;
                            }
                        }
                    }
                    if(top_number_two.length() > top_number_one.length()){
                        //top_number_one是父  top_number_two是子
                        String[] arrStr = StringUtil.arrStr(top_number_two);
                        for(int i=0;i<arrStr.length;i++){
                            listStr.add(arrStr[i]);
                            if(arrStr[i].equals(top_number_one)){
                                //找到了
                                break;
                            }
                        }
                    }
                    if(listStr != null){
                        //这个集合是存放我们要找的推荐路线的会员的number
                        for(int i = 0;i < listStr.size();i++){
                            EmpVO empVO2 =  empDao.findByNumber(listStr.get(i));
                            lists.add(empVO2);
                        }
                        listsDuan.clear();
                        listsDuan.addAll(lists);//最短路线就是这条
                    }
                }else {
                    //没有上下级关系，找共同点并返回数组路线
                    String strArr = StringUtil.arrStr2arrStr2(top_number_one, top_number_two);
                    String[] arrs = strArr.split(",");
                    if(arrs != null){
                        //这个集合是存放我们要找的推荐路线的会员的number
                        for(int i = 0;i < arrs.length;i++){
                            EmpVO empVO2 =  empDao.findByNumber(arrs[i]);
                            lists.add(empVO2);
                        }
                        listsDuan.clear();
                        listsDuan.addAll(lists);//最短路线就是这条
                    }
                }
            }else {
                //说明两个不是一个祖宗
                Map<String, Object> mapTopNumber = new HashMap<String, Object>();
                mapTopNumber.put("top_number", top_number_one.substring(0,4));
                List<EmpVO> list1 = empDao.listtopnumber3(mapTopNumber);
                List<EmpVO> list1Tmp = new ArrayList<EmpVO>();
                for(int i=0;i<list1.size();i++){
                    if(list1.get(i).getTop_number().length() < top_number_one.length()){
                        list1Tmp.add(list1.get(i));
                    }
                    if(list1.get(i).getTop_number().equals(top_number_one)){
                        //找到路线一的最后一个点了
                        list1Tmp.add(list1.get(i));
                        break;
                    }
                }
                for(int i=(list1Tmp.size()-1) ; i>=0; i--){
                    lists.add(list1Tmp.get(i));
                }

                Map<String, Object> mapTopNumber1 = new HashMap<String, Object>();
                mapTopNumber1.put("top_number", top_number_two.substring(0,4));
                List<EmpVO> lists2 = empDao.listtopnumber3(mapTopNumber1);

                if(lists2 != null){
                    for(int i= 0; i<lists2.size();i++){
                        EmpVO empVO2 = lists2.get(i);
                        if(empVO2.getTop_number().length()<top_number_two.length()){
                            lists.add(empVO2);
                        }
                        if(empVO2.getMm_emp_id().equals(empVO1.getMm_emp_id())){
                            //如果找到最后一个了 跳出循环
                            lists.add(empVO2);
                            break;
                        }
                    }
                }

                //最短路线
                //先查询他们是否是好友关系
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("mm_emp_id1", empVO.getMm_emp_id());
                map.put("mm_emp_id2", empVO1.getMm_emp_id());
                map.put("state", "1");
                List<EmpRelateObjVO> listsRe = empRelateDao.lists(map);
                if(listsRe != null && listsRe.size()>0){
                    //说明已经是好友关系了
                    listsDuan.add(empVO);
                    listsDuan.add(empVO1);
                }else {
                    //说明他们彼此不认识，需要通过好友关系结实他
                    Map<String, Object> mapBj1 = new HashMap<String, Object>();
                    mapBj1.put("mm_emp_id1", empVO.getMm_emp_id());
                    mapBj1.put("state", "1");
                    List<EmpRelateObjVO> listbj1 = empRelateDao.listsRelateOne(mapBj1);//我是否在朋友关系表中
                    Map<String, Object> mapBj11 = new HashMap<String, Object>();
                    mapBj11.put("mm_emp_id2", empVO.getMm_emp_id());
                    mapBj11.put("state", "1");
                    List<EmpRelateObjVO> listbj11 = empRelateDao.listsRelateOne(mapBj11);//我是否在朋友关系表中
                    listbj1.addAll(listbj11);

                    Map<String, Object> mapBj2 = new HashMap<String, Object>();
                    mapBj2.put("mm_emp_id2", empVO1.getMm_emp_id());
                    mapBj2.put("state", "1");
                    List<EmpRelateObjVO> listbj2 = empRelateDao.listsRelateTwo(mapBj2);//对方是否在朋友关系表中

                    Map<String, Object> mapBj22 = new HashMap<String, Object>();
                    mapBj22.put("mm_emp_id1", empVO1.getMm_emp_id());
                    mapBj22.put("state", "1");
                    List<EmpRelateObjVO> listbj22 = empRelateDao.listsRelateTwo(mapBj22);//对方是否在朋友关系表中
                    listbj2.addAll(listbj22);

                    if(listbj1 != null && listbj1.size() > 0 && listbj2 != null && listbj2.size() >0){
                        //1.如果他们都在关系表中
                        //用sql语句查询 看看是否有他们两个 是否有关系
                        Map<String, Object> mapBttween = new HashMap<String, Object>();
                        mapBttween.put("mm_emp_id1", empVO.getMm_emp_id());
                        mapBttween.put("state", "1");
                        List<EmpRelateObjVO> listBt = empRelateDao.listsRelateOne(mapBttween);

                        List<EmpRelateObjVO> listBtUs = validateRelates(listBt, empVO1.getMm_emp_id());
                        List<String> emps = new ArrayList<String>();
                        emps.add(empVO.getMm_emp_id());
                        for(EmpRelateObjVO empRelateObjVO:listBtUs){
                            emps.add(empRelateObjVO.getMm_emp_id1());
                            emps.add(empRelateObjVO.getMm_emp_id2());
                        }
//                emps去除重复值
                        HashSet<String> hs = new HashSet<String>(emps); //此时已经去掉重复的数据保存在hashset中

                        for(String empid :hs){
                            //根据ids查询所有会员
                            EmpVO empVO2= empDao.findById(empid);
                            if(!listsDuan.contains(empVO2)){
                                listsDuan.add(empVO2);
                            }
                        }

                    }else {
                        //2.如果他们不在关系表中
//                    listsRenxing 是空的  他朋友比较少 ，
                        listsDuan.addAll(lists);
                    }
                }
            }
        }

        //第二层处理--
        //先查询他们是否是好友关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mm_emp_id1", empVO.getMm_emp_id());
        map.put("mm_emp_id2", empVO1.getMm_emp_id());
        map.put("state", "1");
        List<EmpRelateObjVO> listsRe = empRelateDao.lists(map);
        if(listsRe != null && listsRe.size()>0){
            //说明已经是好友关系了
            listsRenxing.add(empVO);
            listsRenxing.add(empVO1);
        }else {
            //说明他们彼此不认识，需要通过好友关系结实他
            Map<String, Object> mapBj1 = new HashMap<String, Object>();
            mapBj1.put("mm_emp_id1", empVO.getMm_emp_id());
            mapBj1.put("state", "1");
            List<EmpRelateObjVO> listbj1 = empRelateDao.listsRelateOne(mapBj1);//我是否在朋友关系表中
            Map<String, Object> mapBj11 = new HashMap<String, Object>();
            mapBj11.put("mm_emp_id2", empVO.getMm_emp_id());
            mapBj11.put("state", "1");
            List<EmpRelateObjVO> listbj11 = empRelateDao.listsRelateOne(mapBj11);//我是否在朋友关系表中
            listbj1.addAll(listbj11);

            Map<String, Object> mapBj2 = new HashMap<String, Object>();
            mapBj2.put("mm_emp_id2", empVO1.getMm_emp_id());
            mapBj2.put("state", "1");
            List<EmpRelateObjVO> listbj2 = empRelateDao.listsRelateTwo(mapBj2);//对方是否在朋友关系表中

            Map<String, Object> mapBj22 = new HashMap<String, Object>();
            mapBj22.put("mm_emp_id1", empVO1.getMm_emp_id());
            mapBj22.put("state", "1");
            List<EmpRelateObjVO> listbj22 = empRelateDao.listsRelateTwo(mapBj22);//对方是否在朋友关系表中
            listbj2.addAll(listbj22);

            if(listbj1 != null && listbj1.size() > 0 && listbj2 != null && listbj2.size() >0){
                //1.如果他们都在关系表中
                //用sql语句查询 看看是否有他们两个 是否有关系
                Map<String, Object> mapBttween = new HashMap<String, Object>();
                mapBttween.put("mm_emp_id1", empVO.getMm_emp_id());
                mapBttween.put("state", "1");
                List<EmpRelateObjVO> listBt = empRelateDao.listsRelateOne(mapBttween);

                List<EmpRelateObjVO> listBtUs = validateRelates(listBt, empVO1.getMm_emp_id());
                List<String> emps = new ArrayList<String>();
                emps.add(empVO.getMm_emp_id());
                for(EmpRelateObjVO empRelateObjVO:listBtUs){
                    emps.add(empRelateObjVO.getMm_emp_id1());
                    emps.add(empRelateObjVO.getMm_emp_id2());
                }
//                emps去除重复值
                HashSet<String> hs = new HashSet<String>(emps); //此时已经去掉重复的数据保存在hashset中

                for(String empid :hs){
                    //根据ids查询所有会员
                    EmpVO empVO2= empDao.findById(empid);
                    if(!listsRenxing.contains(empVO2)){
                        listsRenxing.add(empVO2);
                    }
                }

            }else {
                //2.如果他们不在关系表中
//                    listsRenxing 是空的 不做任何处理 手机端提示用户  他朋友比较少 ，
                listsRenxing.addAll(lists);
            }
        }

        int tmpI = 0,tmpJ = 0;
        for(int i=0;i<listsRenxing.size();i++){
            EmpVO empVO2 = listsRenxing.get(i);
            if(empVO2.getTop_number().equals(top_number_one)){
                tmpI = i;
            }
        }

        if(listsRenxing!= null && listsRenxing.size()>0){
            listsRenxing.remove(tmpI);
        }

        for(int i=0;i<listsRenxing.size();i++){
            EmpVO empVO2 = listsRenxing.get(i);
            if( empVO2.getTop_number().equals(top_number_two)){
                tmpJ = i;
            }
        }

        if(listsRenxing != null && listsRenxing.size() >0){
            listsRenxing.remove(tmpJ);
        }

        // 倒序
        Collections.sort(listsRenxing, new Comparator<EmpVO>() {
            public int compare(EmpVO arg0, EmpVO arg1) {
                int hits0 = arg0.getTop_number().length();
                int hits1 = arg1.getTop_number().length();
                if (hits1 > hits0) {
                    return 1;
                } else if (hits1 == hits0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        //头部和尾部插入元素
        listsRenxing.add(0, empVO);
        listsRenxing.add(empVO1);

        //最后进行数据处理
        for(EmpVO empVO2:lists){
            if (!StringUtil.isNullOrEmpty(empVO2.getMm_emp_cover())) {
                if (empVO2.getMm_emp_cover().startsWith("upload")) {
                    empVO2.setMm_emp_cover(Constants.URL + empVO2.getMm_emp_cover());
                }else {
                    empVO2.setMm_emp_cover(Constants.QINIU_URL + empVO2.getMm_emp_cover());
                }
            }
        }

        for(EmpVO empVO2:listsRenxing){
            if (!StringUtil.isNullOrEmpty(empVO2.getMm_emp_cover())) {
                if (empVO2.getMm_emp_cover().startsWith("upload")) {
                    empVO2.setMm_emp_cover(Constants.URL + empVO2.getMm_emp_cover());
                }else {
                    empVO2.setMm_emp_cover(Constants.QINIU_URL + empVO2.getMm_emp_cover());
                }
            }
        }

        if(lists.size()==0 && listsRenxing.size() >0){
            lists.addAll(listsRenxing);
        }

        if(lists.size() > 0 && listsRenxing.size() ==0){
            listsRenxing.addAll(lists);
        }

        return  new Object[]{lists, listsRenxing, listsDuan};
    }

    List<EmpRelateObjVO> validateRelates(List<EmpRelateObjVO> listBt, String empid){
        List<EmpRelateObjVO> listBt2 = new ArrayList<EmpRelateObjVO>();
        listBt2.addAll(listBt);
        int i=0;
        if(listBt != null){
            for(EmpRelateObjVO empRelateObjVO : listBt){
                if(empRelateObjVO.getMm_emp_id2().equals(empid)){
                    //是我们最后要求的值
                    return listBt2;
                }
//                else {
//                    i ++;
//                    if(i<10){
//                        Map<String, Object> mapBttween = new HashMap<String, Object>();
//                        mapBttween.put("state", "1");
//                        mapBttween.put("mm_emp_id1", empRelateObjVO.getMm_emp_id2());
//                        List<EmpRelateObjVO> listBt1 =  empRelateDao.listsRelateOne(mapBttween);
//                        validateRelates(listBt1, empid);
//                    }else {
//                        return listBt2;
//                    }
//                }
            }
        }
        return listBt2;
    }

}
