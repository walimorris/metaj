package com.morris.metaj.service.impl;

import com.morris.metaj.service.AwsClientsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class AwsClientsBuilderImpl implements AwsClientsBuilder {
    private static final Logger logger = LoggerFactory.getLogger(AwsClientsBuilderImpl.class);

    public AwsClientsBuilderImpl() {}

    @Override
    public IamClient getIamClient(Region region) {
        return IamClient.builder()
                .region(region)
                .build();
    }

    @Override
    public CloudWatchClient getCloudWatchClient(Region region) {
        return CloudWatchClient.builder()
                .region(region)
                .build();
    }

    @Override
    public QuickSightClient getQuickSightClient(Region region) {
        return QuickSightClient.builder()
                .region(region)
                .build();
    }

    @Override
    public S3Client getS3Client(Region region) {
        return S3Client.builder()
                .region(region)
                .build();
    }
}
