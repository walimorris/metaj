package com.morris.metaj.controller;

import com.morris.metaj.model.MetaInstance;
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
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.GetUserRequest;
import software.amazon.awssdk.services.iam.model.User;
import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.*;

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

        // build cpu utilization alarm for metaj instance if it does not exist
        CloudWatchClient cloudWatchClient = clientsBuilder.getCloudWatchClient(Region.US_WEST_2);
        if (!cloudWatchService.basicCPUMetricAlarmExists(cloudWatchClient, instanceId)) {
            cloudWatchService.putBasicCPUMetricAlarm(cloudWatchClient, instanceId);
        }
        cloudWatchClient.close();
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
