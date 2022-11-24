package com.morris.metaj.service;

/**
 * {@code com/morris/metaj/service/MetaJS3Service.java} contains various AWS S3 functionalities to work with
 * S# buckets in metaj
 */
public interface MetaJS3Service {

    /**
     * Creates an S3 bucket to hold BasicCPUMetric data.
     *
     * @return boolean
     */
    boolean createBasicCPUMetricBucket();
}
