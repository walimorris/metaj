package com.morris.metaj.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;

/**
 * {@code com/morris/metaj/service/AwsClientBuilder.java} builds Clients for various AWS services such as
 * cloudwatch, s3, and so on.
 */
public interface AwsClientsBuilder {

    /**
     * Get AWS cloudwatch client.
     *
     * @param region region to use for client
     * @return {@link CloudWatchClient}
     */
    CloudWatchClient getCloudWatchClient(Region region);
}
