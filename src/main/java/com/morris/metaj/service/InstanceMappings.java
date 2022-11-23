package com.morris.metaj.service;

import java.io.IOException;
import java.util.Map;

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
}
