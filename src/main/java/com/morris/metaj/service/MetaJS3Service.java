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
     * @param s3Client {@link S3Client}
     * @return boolean
     */
    boolean createBasicCPUMetricBucket(S3Client s3Client);
}
