package com.liangxunwang.unimanager.mvc;

/**
 * Created by zhl on 2016/8/10.
 */
public class MainTest {
    public static  void main(String[] arrs){
        System.out.print(arrStr2arrStr2("0001000100020001", "0001000100010001"));
    }

    //根据两个topnumber 找共同点
    public static String arrStr2arrStr2(String str1, String str2){

        //拆分
        int count1 = str1.length()/4;//取整
        String[] arr1 = new String[count1];
        arr1[0] = str1;
        for(int i=1;i<count1;i++){
            if(str1.length()>4){
                str1 = str1.substring(0,str1.length() - 4);//获得上一个父
                arr1[i] = str1;
            }
        }

        int count2 = str2.length()/4;//取整
        String[] arr2 = new String[count2];
        arr2[0] = str2;
        for(int i=1;i<count2;i++){
            if(str2.length()>4){
                str2 = str2.substring(0,str2.length() - 4);//获得上一个父
                arr2[i] = str2;
            }
        }

        //找共同点
        String strGtd = "";
        for(int i = 0; i <arr1.length; i ++){
            String strTmp = arr1[i];
            boolean flag = false;
            for(int j=0;j < arr2.length ; j++){
                String strTmp2 = arr2[j];
                if(strTmp2.equals(strTmp)){
                    //找到共同点了
                    strGtd = strTmp;
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }

        String string1 = "";
        for(int i= 0;i < arr1.length ;i++){
            if(!arr1[i].equals(strGtd)){
                string1 += arr1[i] + ",";
            }
            if(arr1[i].equals(strGtd)){
                break;
            }
        }

        string1 += strGtd + ",";

        String strss = "";
        for(int i = 0;i < arr2.length ;i++){
            if(!arr2[i].equals(strGtd)){
                strss += arr2[i] + ",";
            }
            if(arr2[i].equals(strGtd)){
                break;
            }
        }
        String[] strrr = strss.split(",");
        for(int i = (strrr.length-1); i>=0 ; i--){
            string1 += strrr[i] + ",";
        }

        return string1;
    }
}
