package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodplanner.recipe.crawler.recipecrawler.repositories.S3Repository;

@Component
public abstract class BasicWebCrawler {

    @Autowired
    S3Repository s3Repo;

    public abstract void getPageLinks(String url);

    public S3Repository getS3Repo() {
        return s3Repo;
    }

    public void setS3Repo(S3Repository s3Repo) {
        this.s3Repo = s3Repo;
    }

}
