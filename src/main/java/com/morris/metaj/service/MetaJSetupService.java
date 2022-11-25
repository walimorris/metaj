package com.morris.metaj.service;

import software.amazon.awssdk.regions.Region;

/**
 * {@code com/morris/metaj/service/MetaJSetupService.java} contains various metaj application setup functionalities
 */
public interface MetaJSetupService {

    /**
     * Sets up entire metaj application settings such as data storage, logging, metric alarms
     * and more across the entire application.
     *
     * @param instanceId instance id of the EC2 instance running metaj
     * @param metajRegion the region the instance is in
     */
    void setupMetaJ(String instanceId, Region metajRegion);
}
