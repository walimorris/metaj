package com.morris.metaj.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Profile("test")
public class InstanceMappingTest {

    @Autowired
    private InstanceMappingsImpl instanceMappings;


    public static final String BLOCK_DEVICE_MAPPING_TEST_1 = "blockDeviceMappingblock-device-mapping: ami: /dev/sda1 ephemeral0: sdb ephemeral1: sdc root: /dev/sda1";
    public static final String BLOCK_DEVICE_MAPPING_TEST_2 = "blockDeviceMappingblock-device-mapping: ami: /dev/sda1 ephemeral0: sdb ephemeral1: sdc root:";
    public static final String INSTANCE_PROPERTY_1 = "kernelId: 123456789";
    public static final String INSTANCE_VALUE_1 = "123456789";
    public static final String TEST_REGION_PROPERTY_1 = "availabilityZoneplacement: us-west-2c";
    public static final String TEST_REGION_PROPERTY_2 = "availabilityZoneplacement: us-west-3b";
    public static final Region TEST_REGION_PROPERTY_RESULT_1 = Region.US_WEST_2;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(instanceMappings);
    }

    @Test
    void getBlockDeviceMappingsMap() throws IOException {
        Map<String, String> expectedMapping = new HashMap<>();
        expectedMapping.put("ami", "/dev/sda1");
        expectedMapping.put("ephemeral0", "sdb");
        expectedMapping.put("ephemeral1", "sdc");
        expectedMapping.put("root", "/dev/sda1");

        Map<String, String> actualMapping = instanceMappings.getBlockDeviceMappingsMap(BLOCK_DEVICE_MAPPING_TEST_1);
        Assertions.assertEquals(expectedMapping, actualMapping);
    }

    @Test
    void getBlockDeviceMappingsMapWithUnevenProperties() throws IOException {
        Map<String, String> actualMapping = instanceMappings.getBlockDeviceMappingsMap(BLOCK_DEVICE_MAPPING_TEST_2);
        Assertions.assertNull(actualMapping);
    }

    @Test
    void getInstanceValue() throws IOException {
        String actualValue = instanceMappings.getInstanceValue(INSTANCE_PROPERTY_1);
        Assertions.assertEquals(INSTANCE_VALUE_1, actualValue);
    }

    @Test
    void getInstanceRegion() throws IOException {
        Region actualValue = instanceMappings.getInstanceRegion(TEST_REGION_PROPERTY_1);
        Assertions.assertEquals(TEST_REGION_PROPERTY_RESULT_1, actualValue);
    }

    @Test
    void getInstanceRegionWithIncorrectRegion() throws IOException {
        Region actualValue = instanceMappings.getInstanceRegion(TEST_REGION_PROPERTY_2);
        Assertions.assertNull(actualValue);
    }
}
