package com.morris.metaj.service.impl;

import com.morris.metaj.service.MetaJS3Service;
import com.morris.metaj.service.MetaJSetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class MetaJSetupServiceImpl implements MetaJSetupService {
    private static final Logger logger = LoggerFactory.getLogger(MetaJS3ServiceImpl.class);

    @Autowired
    CloudWatchServiceImpl cloudWatchService;

    @Autowired
    MetaJS3Service metaJS3Service;

    @Autowired
    AwsClientsBuilderImpl clientsBuilder;

    @Override
    public void setupMetaJ(String instanceId, Region metajRegion) {
        S3Client s3Client = clientsBuilder.getS3Client(metajRegion);
        boolean isBasicCPUMetricBucketCreated = metaJS3Service.checkBasicCpuUtilBucketExists(s3Client);
        if (!isBasicCPUMetricBucketCreated) {
            isBasicCPUMetricBucketCreated = metaJS3Service.createBasicCPUMetricBucket(s3Client);
        }

        CloudWatchClient cloudWatchClient = clientsBuilder.getCloudWatchClient(metajRegion);
        boolean isBasicCPUMetricAlarmCreated = cloudWatchService.basicCPUMetricAlarmExists(cloudWatchClient, instanceId);
        if (!isBasicCPUMetricAlarmCreated) {
            isBasicCPUMetricAlarmCreated = cloudWatchService.putBasicCPUMetricAlarm(cloudWatchClient, instanceId);
        }

        if (isBasicCPUMetricBucketCreated && isBasicCPUMetricAlarmCreated) {
            logger.info("bucket: '{}' and cloud watch alarm: '{}' created", MetaJS3ServiceImpl.METAJ_BASIC_CPU_UTIL_BUCKET,
                    CloudWatchServiceImpl.ALARM_NAME_BASIC_CPU_UTIL);
        }

        s3Client.close();
        if (cloudWatchClient != null) {
            cloudWatchClient.close();
        }
    }
}
