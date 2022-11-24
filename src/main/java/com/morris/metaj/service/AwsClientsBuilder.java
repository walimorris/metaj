package com.morris.metaj.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * {@code com/morris/metaj/service/AwsClientBuilder.java} builds Clients for various AWS services such as
 * cloudwatch, Iam, and so on.
 */
public interface AwsClientsBuilder {

    /**
     * Get AWS cloudwatch client.
     *
     * @param region region to use for client
     * @return {@link CloudWatchClient}
     */
    CloudWatchClient getCloudWatchClient(Region region);

    /**
     * Get AWS quicksight client.
     *
     * @param region region to use for client
     * @return {@link QuickSightClient}
     */
    QuickSightClient getQuickSightClient(Region region);

    /**
     * Get AWS Iam Client.
     *
     * @param region region to use for client
     * @return {@link IamClient}
     */
    IamClient getIamClient(Region region);

    /**
     * Get AWS S3 Client.
     *
     * @param region region to use for client.
     * @return {@link S3Client}
     */
    S3Client getS3Client(Region region);
}
