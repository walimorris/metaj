package com.morris.metaj.service.impl;

import com.morris.metaj.service.InstanceMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class InstanceMappingsImpl implements InstanceMappings {
    private static final Logger logger = LoggerFactory.getLogger(InstanceMappingsImpl.class);

    public InstanceMappingsImpl() {}

    @Override
    public Map<String, String> getBlockDeviceMappingsMap(String blockDeviceMapping) throws IOException {
        String[] split = removeFirstIndex(blockDeviceMapping.split(":"));
        String splitStr = stringifyArray(split);
        return mapDeviceMappings(splitStr.split(" "));
    }

    /**
     * Removes the first index from the given array.
     *
     * @param array String[]
     * @return String[]
     */
    private String[] removeFirstIndex(String[] array) {
        String[] arrayWithFirstIndexRemoved = new String[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            arrayWithFirstIndexRemoved[i - 1] = array[i];
        }
        return arrayWithFirstIndexRemoved;
    }

    /**
     * Builds a {@link String} given a String[] array of values.
     *
     * @param array String[]
     * @return {@link String}
     */
    private String stringifyArray(String[] array) {
        StringBuilder stringyBuilder = new StringBuilder();
        for (String str : array) {
            stringyBuilder.append(str);
        }
        return stringyBuilder.toString().trim();
    }

    /**
     * Given a String[] of EC2 instance device properties a {@link Map} is built mapping each device
     * property to its value. Given the nature of how ec2-meta utils returns the value of calling a
     * virtual instance's meta-data for device mappings, the given array should consist of the prop
     * followed by its value. In theory, the given array with the device properties and values should
     * be even and easily mapped as device property values should follow its device property.
     *
     * @param mappings String[] device properties and values
     *
     * @return {@link Map} of devices properties mapped to its value
     *
     * @throws IOException correct output can not be given with uneven array which should consist of
     * a EC2 virtual instance's properties and value companion.
     */
    private Map<String, String> mapDeviceMappings(String[] mappings) throws IOException {
        if (mappings.length % 2 != 0) {
            throw new IOException("Uneven device mapping properties, check that all device properties map to an associated value");
        }
        Map<String, String> deviceMappings = new HashMap<>();
        for (int i = 0; i < mappings.length; i+=2) {
            deviceMappings.put(mappings[i], mappings[i+1]);
        }
        return deviceMappings;
    }
}
