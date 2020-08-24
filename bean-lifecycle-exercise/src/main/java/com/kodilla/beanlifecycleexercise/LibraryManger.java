package com.kodilla.beanlifecycleexercise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LibraryManger implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("Name of the bean: " + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader instance passed");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory instance passed");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Method destroy has been called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Properties are set");
    }

    @PostConstruct
    public void examplePostConstructMethod() {
        System.out.println("Method with @PostConstruct annotation");
    }

    @PreDestroy
    public void examplePreDestroyMethod() {
        System.out.println("Method with @PreDestroy annotation");
    }
}
