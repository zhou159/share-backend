package com.share.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        SpringUtil.beanFactory = beanFactory;
//    }
//
//    public static ConfigurableListableBeanFactory getBeanFactory() {
//        return beanFactory;
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T getBean(String name) throws BeansException {
//        return (T) getBeanFactory().getBean(name);
//    }
//
//    public static <T> T getBean(Class<T> clz) throws BeansException {
//        T result = (T) getBeanFactory().getBean(clz);
//        return result;
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;

    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz){
        return (T)applicationContext.getBean(clazz);
    }

}
