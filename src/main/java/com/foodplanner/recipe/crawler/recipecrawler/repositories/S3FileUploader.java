package com.foodplanner.recipe.crawler.recipecrawler.repositories;

import java.util.HashSet;

public interface S3FileUploader {

    void uploadFile(HashSet<String> hashSet, String bucketName,
                    String fileName);
}
