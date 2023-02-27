package com.zh.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
public class WebUtils {

    public static void zhan() {

    }

    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            System.out.println("传入的值有误");
//            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     *	把Map 中的值注入到对应的JavaBean 属性中。
     *	@param value
     * @param bean
*/
    public static <T> T copyParamToBean(Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到user 对象中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) { e.printStackTrace();
        }
        return bean;
    }

}
