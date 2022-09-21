package com.foodplanner.recipe.crawler.recipecrawler.repositories;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

@Service("generic")
public class S3FileUploaderGenericImpl implements S3FileUploader{

    final S3Repository s3Repository;

    public S3FileUploaderGenericImpl(S3Repository s3Repository) {
        this.s3Repository = s3Repository;
    }


    @Override
    public void uploadFile(HashSet<String> hashSet, String bucketName,
                           String fileName) {
        Path path = Paths.get(bucketName + fileName + ".txt");

        try {
            Files.write(path, hashSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AmazonS3 s3Client = s3Repository.getS3Client();
        if (!s3Client.doesBucketExistV2(bucketName)) {
            s3Client.createBucket(bucketName);
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName, fileName, path.toFile())
                .withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putObjectRequest);
    }
}
