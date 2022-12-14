package com.morris.metaj.service.impl;

import com.morris.metaj.service.InstanceMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public String getInstanceValue(String property) throws IOException {
        Map<String, String> instanceMap = mapDeviceMappingsWithSpaceSplit(property);
        return getFirstKeyValueFromMap(instanceMap);
    }

    @Override
    public Region getInstanceRegion(String property) throws IOException {
        Map<String, String> instanceMap = mapDeviceMappingsWithSpaceSplit(property);
        String propertyValue = getFirstKeyValueFromMap(instanceMap);
        propertyValue = propertyValue.substring(0, propertyValue.length() - 1);
        List<Region> regions = Region.regions();

        for (Region region : regions) {
            if (region.toString().equalsIgnoreCase(propertyValue)) {
                return region;
            }
        }
        return null;
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
     */
    private Map<String, String> mapDeviceMappings(String[] mappings) {
        if (mappings.length % 2 != 0) {
            return null;
        }
        Map<String, String> deviceMappings = new HashMap<>();
        for (int i = 0; i < mappings.length; i+=2) {
            deviceMappings.put(mappings[i], mappings[i+1]);
        }
        return deviceMappings;
    }

    /**
     * Get key/value mapping of given device instance property after splitting the property from a
     * space regex (a standard empty space in text).
     *
     * @param property given metadata instance property
     *
     * @return {@link Map}
     * @throws IOException throws IOException
     */
    private Map<String, String> mapDeviceMappingsWithSpaceSplit(String property) throws IOException {
        return mapDeviceMappings(property.split(" "));
    }

    /**
     * Get the first key's value from a {@link Map}
     *
     * @param map {@link Map}
     * @return {@link String}
     */
    private String getFirstKeyValueFromMap(Map<String, String> map) {
        return map.get(map.keySet().toArray()[0]);
    }
}
