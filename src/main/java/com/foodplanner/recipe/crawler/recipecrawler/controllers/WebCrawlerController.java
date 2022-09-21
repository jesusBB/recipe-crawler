package com.foodplanner.recipe.crawler.recipecrawler.controllers;

import com.foodplanner.recipe.crawler.recipecrawler.repositories.S3FileUploader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodplanner.recipe.crawler.recipecrawler.repositories.ClientS3ObjectConfig;
import com.foodplanner.recipe.crawler.recipecrawler.repositories.S3ObjectConfig;
import com.foodplanner.recipe.crawler.recipecrawler.repositories.S3Repository;
import com.foodplanner.recipe.crawler.recipecrawler.request.WebCrawlerRequest;
import com.foodplanner.recipe.crawler.recipecrawler.service.impl.BeanFactoryDynamicAutowireService;
import com.foodplanner.recipe.crawler.recipecrawler.service.impl.WebCrawler;

@RestController
public class WebCrawlerController {
    private static final Logger logger = LogManager
            .getLogger(WebCrawlerController.class);

    public WebCrawlerController(BeanFactoryDynamicAutowireService beanFactory, ClientS3ObjectConfig clientS3Config, S3Repository s3Repository, S3FileUploader s3FileUploader) {
        this.beanFactory = beanFactory;
        this.clientS3Config = clientS3Config;
        this.s3Repository = s3Repository;
        this.s3FileUploader = s3FileUploader;
    }

//  @Autowired
    BeanFactoryDynamicAutowireService beanFactory;

//    @Autowired
    ClientS3ObjectConfig clientS3Config;

//    @Autowired
    S3Repository s3Repository;

 //   @Autowired
    @Qualifier("generic")
    S3FileUploader s3FileUploader;
    @PostMapping("url")
    public void getRecipesFromURL(@RequestBody WebCrawlerRequest request) {
        // System.out.println("URL to search:" + request.getUrl());
        logger.error("URL to search:" + request.getUrl());
        logger.warn("URL to search:" + request.getUrl());
        System.out.println("Client to search:" + request.getClient());

        WebCrawler webCrawler = beanFactory.getService(request.getClient());
        webCrawler.getPageLinks(request.getUrl());

        S3ObjectConfig s3ObjectConfig = clientS3Config.getClientS3ConfigsMap()
                .get(request.getClient());

        /*s3Repository.uploadFile(webCrawler.getLinks(),
                s3ObjectConfig.getBucketName(), s3ObjectConfig.getFileName());*/
        s3FileUploader.uploadFile(webCrawler.getLinks(),
                  s3ObjectConfig.getBucketName(), s3ObjectConfig.getFileName());

    }
}
