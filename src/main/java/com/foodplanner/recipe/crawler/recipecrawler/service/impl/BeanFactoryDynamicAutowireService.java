package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanFactoryDynamicAutowireService {
    private static final String SERVICE_NAME_SUFFIX = "WebCrawler";

    private final BeanFactory beanFactory;

    @Autowired
    public BeanFactoryDynamicAutowireService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public WebCrawler getService(String client) {
        return beanFactory.getBean(
                getClientServiceBeanName(client), WebCrawler.class);
    }

    private String getClientServiceBeanName(String client) {
        return client + SERVICE_NAME_SUFFIX;
    }

}
