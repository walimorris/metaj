package com.morris.metaj.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class MetaJS3ServiceImplTest {
    private static S3Mock api;
    private static AmazonS3Client client;

    @Autowired
    private MetaJS3ServiceImpl metaJS3Service;

    public static final int TEST_PORT = 8081;
    public static final String TEST_ENDPOINT = "http://localhost:8081";
    public static final String TEST_REGION = "us-west-2";

    public static final String TEST_BUCKET_1 = "test-bucket-1";
    public static final String TEST_BUCKET_2 = "test-bucket-2";
    public static final String TEST_BUCKET_3 = "test-bucket-3";
    public static final String TEST_BUCKET_4 = "test-bucket-4";

    @BeforeAll
    public static void setup() {
        System.out.println("Setting up before event");
        api = new S3Mock.Builder().withPort(TEST_PORT).withInMemoryBackend().build();
        api.start();

        EndpointConfiguration endpoint = new EndpointConfiguration(TEST_ENDPOINT, TEST_REGION);
        client = (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .build();

        client.createBucket(TEST_BUCKET_1);
        client.createBucket(TEST_BUCKET_2);
        client.createBucket(TEST_BUCKET_3);
    }

    @AfterAll
    public static void tearDown() {
        api.shutdown();
    }


    @Test
    void contextLoads() {
        Assertions.assertNotNull(metaJS3Service);
    }

    @Test
    void createBasicCPUMetricBucket() {
        client.createBucket(MetaJS3ServiceImpl.METAJ_BASIC_CPU_UTIL_BUCKET);
        boolean bucketExists = client.doesBucketExist(MetaJS3ServiceImpl.METAJ_BASIC_CPU_UTIL_BUCKET);
        Assertions.assertTrue(bucketExists);
    }

    @Test
    void checkBucketExists() {
        List<Bucket> buckets = client.listBuckets();
        Assertions.assertNotNull(buckets);
        boolean bucketExists = checkBuckets(buckets, TEST_BUCKET_1);
        Assertions.assertTrue(bucketExists);
    }

    @Test
    void checkBucketExistsIsFalseThatHasNotBeenCreatedInS3() {
        List<Bucket> buckets = client.listBuckets();
        Assertions.assertNotNull(buckets);
        boolean bucketExists = checkBuckets(buckets, TEST_BUCKET_4);
        Assertions.assertFalse(bucketExists);
    }

    @Test
    void checkBasicCpuUtilBucketExists() {
        List<Bucket> buckets = client.listBuckets();
        Assertions.assertNotNull(buckets);
        boolean bucketExists = checkBuckets(buckets, MetaJS3ServiceImpl.METAJ_BASIC_CPU_UTIL_BUCKET);
        Assertions.assertTrue(bucketExists);
    }

    public boolean checkBuckets(List<Bucket> buckets, String bucket) {
        boolean bucketExists = false;
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket)) {
                bucketExists = true;
            }
        }
        return bucketExists;
    }
}
