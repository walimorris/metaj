package com.morris.metaj.service.impl;

import com.morris.metaj.service.MetaJS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketResponse;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

@Service
public class MetaJS3ServiceImpl implements MetaJS3Service {
    private static final Logger logger = LoggerFactory.getLogger(MetaJS3ServiceImpl.class);

    public static final String METAJ_BASIC_CPU_UTIL_BUCKET = "metaj-basic-cpu-util-bucket";

    @Override
    public boolean createBasicCPUMetricBucket(S3Client client) {
        S3Waiter waiter = client.waiter();

        CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                .bucket(METAJ_BASIC_CPU_UTIL_BUCKET)
                .build();
        client.createBucket(bucketRequest);

        HeadBucketRequest bucketWaiter = HeadBucketRequest.builder()
                .bucket(METAJ_BASIC_CPU_UTIL_BUCKET)
                .build();

        // Wait until the bucket is created and print out the response.
        boolean isResponsePresent = false;
        WaiterResponse<HeadBucketResponse> waiterResponse = waiter.waitUntilBucketExists(bucketWaiter);
        if (waiterResponse.matched().response().isPresent()) {
            isResponsePresent = true;
        }
        return isResponsePresent;
    }
}
