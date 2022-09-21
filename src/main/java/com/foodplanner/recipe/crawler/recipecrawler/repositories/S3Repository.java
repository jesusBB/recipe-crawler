package com.foodplanner.recipe.crawler.recipecrawler.repositories;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class S3Repository {

    @Autowired
    S3RepositoryConfiguration s3RepoConfig;

    public AmazonS3 getS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                s3RepoConfig.getAccessKey(),
                s3RepoConfig.getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(s3RepoConfig.getRegion())
                .withCredentials(
                        new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    /*public void uploadFile(HashSet<String> hashSet, String bucketName,
            String fileName) {
        Path path = Paths.get(bucketName + fileName + ".txt");

        try {
            Files.write(path, hashSet);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AmazonS3 s3Client = this.getS3Client();
        if (!s3Client.doesBucketExistV2(bucketName)) {
            s3Client.createBucket(bucketName);
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName, fileName, path.toFile())
                        .withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putObjectRequest);
    }*/
}
