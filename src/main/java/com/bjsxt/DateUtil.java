package com.bjsxt;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式装换工具类
 * @author lvyelanshan
 * @create 2019-11-05 19:11
 */
public class DateUtil {
    /**
     * Date to Stirng
     * 将传入的时间按照指定格式转换成字符串对象
     */
    public static String dateToString(String pattern, Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      return simpleDateFormat.format(date);
    }

    /**
     * String to Date
     * 将传入的String类型的时间按照指定格式转换为时间对象
     */
    public static Date stringToDate(String pattern,String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }
}
