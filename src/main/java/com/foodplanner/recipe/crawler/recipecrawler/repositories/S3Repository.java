package com.foodplanner.recipe.crawler.recipecrawler.repositories;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Repository {

    @Value(value = "${cloud.aws.credentials.profile-name}")
    String awsProfile;
    @Autowired
    S3RepositoryConfiguration s3RepoConfig;
    public AmazonS3 getS3Client() {

        ProfileCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider(awsProfile);

        /*AWSCredentials awsCredentials = new BasicAWSCredentials(
                s3RepoConfig.getAccessKey(),
                s3RepoConfig.getSecretKey());
        /*return AmazonS3ClientBuilder
                .standard()
                .withRegion(s3RepoConfig.getRegion())
                .withCredentials(
                        new AWSStaticCredentialsProvider(awsCredentials))
                .build();*/
        return AmazonS3ClientBuilder.standard()./*withRegion(s3RepoConfig.getRegion()).*/withCredentials(awsCredentialsProvider).build();

    }

}
