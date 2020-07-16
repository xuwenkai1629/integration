package com.integration.minio;

import io.minio.*;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * 描述
 * Title: MinioUtils
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/14
 */
@Slf4j
@Component
public class MinioUtils {

    @Autowired
    private MinioClient client;

    @Autowired
    private MinioProp minioProp;


    @SneakyThrows
    public void createBucket(String bucketName) {
        boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            client.makeBucket(MakeBucketArgs.builder().objectLock(true).bucket(bucketName).build());
        }
    }

    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return client.listBuckets();
    }


    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().expiry(expires).bucket(bucketName).object(objectName).build());
    }

    @SneakyThrows
    public void putObject(String bucketName, String filename, InputStream inputStream) {
        client.putObject(PutObjectArgs.builder().bucket(bucketName).object(filename).stream(inputStream, inputStream.available(), -1).contentType(ContentTypeUtil.getContentType(inputStream)).build());
    }

    @SneakyThrows
    public void setBucketReadWrite(String bucketName, String prefix) {
        client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(prefix).build());
    }

    @SneakyThrows
    public void uploadObject(String bucketName, String object, String filename) {
        client.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(object)
                        .filename(filename)
                        .build());
    }

    @SneakyThrows
    public void uploadImage(String bucketName, String fileName, InputStream inputStream) {
        client.putObject(PutObjectArgs.builder().bucket(bucketName).contentType("image/jpeg").stream(inputStream, inputStream.available(), -1).object(fileName).build());
    }

}
