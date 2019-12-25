package com.edu.wzh.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContextUtils implements ApplicationContextAware {
    private  static  ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyApplicationContextUtils.applicationContext=applicationContext;
    }

    public  static  Object getBean(String beanName){
        return  applicationContext.getBean(beanName);
    }
}
