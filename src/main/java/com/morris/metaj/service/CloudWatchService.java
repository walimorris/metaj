package com.morris.metaj.service;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;

/**
 * {@code com/morris/metaj/service/CloudWatchService.java} contains various methods to process AWS
 * cloudwatch functionalities on metaj.
 */
public interface CloudWatchService {

    /**
     * Places a basic CPU Metric Alarm on AWS Cloudwatch for monitoring the given instance.
     *
     * @param client {@link CloudWatchClient}
     * @param instanceId {@link String} of the instance Cloudwatch should monitor
     *
     * @return boolean
     */
    boolean putBasicCPUMetricAlarm(CloudWatchClient client, String instanceId);

    /**
     * Checks that the BasicCPUMetric alarm exists for a given instance.
     *
     * @param client {@link CloudWatchClient}
     * @param instanceId {@link String} of the given instance to check
     *
     * @return boolean
     */
    boolean basicCPUMetricAlarmExists(CloudWatchClient client, String instanceId);
}
