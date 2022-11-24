package com.morris.metaj.controller;

import com.morris.metaj.model.MetaInstance;
import com.morris.metaj.service.MetaJS3Service;
import com.morris.metaj.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meta")
public class MetaInstanceController {
    private static final Logger logger = LoggerFactory.getLogger(MetaInstanceController.class);

    @Autowired
    private InstanceInitializerImpl instanceInitializer;

    @Autowired
    CloudWatchServiceImpl cloudWatchService;

    @Autowired
    MetaJS3Service metaJS3Service;

    @Autowired
    AwsClientsBuilderImpl clientsBuilder;

    @Autowired
    InstanceMappingsImpl instanceMappings;

    @Autowired
    MetaJAwsIamServiceImpl metaJAwsIamService;

    @GetMapping("/instance")
    public MetaInstance getMetaInstance() throws IOException {
        MetaInstance metaInstance = instanceInitializer.initMetaInstance();
        String instanceId = instanceMappings.getInstanceValue(metaInstance.getId());

        String accountId = metaJAwsIamService.getAccountId();
        Region metajRegion = instanceMappings.getInstanceRegion(metaInstance.getAvailabilityZone());

        boolean isBasicCPUMetricBucketCreated;
        S3Client s3Client = clientsBuilder.getS3Client(metajRegion);
        if (!metaJS3Service.checkBasicCpuUtilBucketExists(s3Client)) {
            isBasicCPUMetricBucketCreated = metaJS3Service.createBasicCPUMetricBucket(s3Client);
        } else {
            isBasicCPUMetricBucketCreated = true;
        }

        CloudWatchClient cloudWatchClient = null;
        if (isBasicCPUMetricBucketCreated) {
            cloudWatchClient = clientsBuilder.getCloudWatchClient(metajRegion);
            if (!cloudWatchService.basicCPUMetricAlarmExists(cloudWatchClient, instanceId)) {
                cloudWatchService.putBasicCPUMetricAlarm(cloudWatchClient, instanceId);
            }
        }
        s3Client.close();

        if (cloudWatchClient != null) {
            cloudWatchClient.close();
        }
        return metaInstance;
    }

    @GetMapping("/test")
    public ResponseEntity<List<String>> getTest() {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        return ResponseEntity.ok(list);
    }
}
