package com.morris.metaj.controller;

import com.morris.metaj.model.MetaInstance;
import com.morris.metaj.service.impl.InstanceInitializerImpl;
import com.morris.metaj.service.impl.InstanceMappingsImpl;
import com.morris.metaj.service.impl.MetaJAwsIamServiceImpl;
import com.morris.metaj.service.impl.MetaJSetupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;

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
    InstanceMappingsImpl instanceMappings;

    @Autowired
    MetaJAwsIamServiceImpl metaJAwsIamService;

    @Autowired
    MetaJSetupServiceImpl metaJSetupService;

    @GetMapping("/instance")
    public MetaInstance getMetaInstance() throws IOException {
        MetaInstance metaInstance = instanceInitializer.initMetaInstance();
        String instanceId = instanceMappings.getInstanceValue(metaInstance.getId());

        String accountId = metaJAwsIamService.getAccountId();
        Region metajRegion = instanceMappings.getInstanceRegion(metaInstance.getAvailabilityZone());

        metaJSetupService.setupMetaJ(instanceId, metajRegion);
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
