package com.morris.metaj.service;

import software.amazon.awssdk.services.s3.S3Client;

/**
 * {@code com/morris/metaj/service/MetaJS3Service.java} contains various AWS S3 functionalities to work with
 * S# buckets in metaj
 */
public interface MetaJS3Service {

    /**
     * Creates an S3 bucket to hold BasicCPUMetric data.
     *
     * @param s3Client {@link S3Client} created from metaj global region
     * @return boolean
     */
    boolean createBasicCPUMetricBucket(S3Client s3Client);

    /**
     * Checks that a given bucket exists.
     *
     * @param client {@link S3Client} created from metaj global region
     * @param bucketName name of bucket
     * @return boolean
     */
    boolean checkBucketExists(S3Client client, String bucketName);

    /**
     * Check that Basic metaj CPU Utilization bucket exists
     *
     * @param client {@link S3Client} created from metaj global region
     * @return boolean
     */
    boolean checkBasicCpuUtilBucketExists(S3Client client);
}
