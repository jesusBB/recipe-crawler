package com.foodplanner.recipe.crawler.recipecrawler.repositories;

public class S3ObjectConfig {
    private String bucketName;

    private String fileName;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
