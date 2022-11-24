package com.morris.metaj.service.impl;

import com.morris.metaj.service.AwsClientsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;

@Service
public class AwsClientsBuilderImpl implements AwsClientsBuilder {
    private static final Logger logger = LoggerFactory.getLogger(AwsClientsBuilderImpl.class);

    public AwsClientsBuilderImpl() {}

    @Override
    public CloudWatchClient getCloudWatchClient(Region region) {
        return CloudWatchClient.builder()
                .region(region)
                .build();
    }
}
