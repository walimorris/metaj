package com.morris.metaj.service;

import java.io.IOException;
import java.util.Map;

import com.morris.metaj.model.MetaInstance;
import software.amazon.awssdk.regions.Region;

/**
 * {@code com/morris/metaj/service/InstanceMappings.java} takes various ec2-metadata util commands' output that has a mapping structure,
 * which is in the form of a string, and builds a {@link Map} object of each property and its value.
 */
public interface InstanceMappings {

    /**
     * Maps an EC2 instances' block device properties and values.
     *
     * @param blockDeviceMappings command output from ec2-metadata instance block device mapping command
     *
     * @return {@link Map} of device properties and its value
     * @throws IOException throws IOException
     *
     * @see InstanceCommands
     * @see CommandExecutor
     */
    Map<String, String> getBlockDeviceMappingsMap(String blockDeviceMappings) throws IOException;

    /**
     * Given the nature of EC2-metadata utils output from commands, an individual instance will return
     * string output that contains that metadata property's name and value seperated with a colon. This
     * method parses that output and returns only the property's value. A Given property should come
     * from an ec2-metadata command output executed on a single instance.
     *
     * @param property output from ec2-metadata utils command
     *
     * @return {@link String}
     * @throws IOException throws IOException
     *
     * @see MetaInstance
     */
    String getInstanceValue(String property) throws IOException;

    /**
     * Get {@link Region} from ec2-metadata availability zone command output.
     *
     * @param property output from ec2-metadata utils availability zone
     *                 command
     *
     * @return {@link Region}
     * @throws IOException throws IOException
     */
    Region getInstanceRegion(String property) throws IOException;
}
