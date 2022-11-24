package com.morris.metaj.service.impl;

import com.morris.metaj.service.CloudWatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

@Service
public class CloudWatchServiceImpl implements CloudWatchService {
    private static final Logger logger = LoggerFactory.getLogger(CloudWatchServiceImpl.class);

    public static final String ALARM_NAME_BASIC_CPU_UTIL = "metaj-basic-cpu-util";

    @Override
    public void putBasicCPUMetricAlarm(CloudWatchClient client, String instanceId) {
        try {
            Dimension dimension = Dimension.builder()
                    .name(instanceId)
                    .value(instanceId).build();

            PutMetricAlarmRequest request = PutMetricAlarmRequest.builder()
                    .alarmName(String.format("%s-%s", ALARM_NAME_BASIC_CPU_UTIL, instanceId))
                    .comparisonOperator(ComparisonOperator.GREATER_THAN_THRESHOLD)
                    .evaluationPeriods(1)
                    .metricName("CPUUtilization")
                    .namespace("AWS/EC2")
                    .period(60)
                    .statistic(Statistic.AVERAGE)
                    .threshold(70.0)
                    .actionsEnabled(false)
                    .alarmDescription("Alarm when CPU Utilization exceeds 70%")
                    .unit(StandardUnit.SECONDS)
                    .dimensions(dimension)
                    .build();

            client.putMetricAlarm(request);
            logger.info("Successfully created alarm on {} with name {}", instanceId, ALARM_NAME_BASIC_CPU_UTIL);
        } catch (CloudWatchException e) {
            logger.error("Error creating cloudwatch alarm for instance {} with alarn name {}", instanceId, ALARM_NAME_BASIC_CPU_UTIL);
        }
    }

    @Override
    public boolean basicCPUMetricAlarmExists(CloudWatchClient client, String instanceId) {
        String alarmName = String.format("%s-%s", ALARM_NAME_BASIC_CPU_UTIL, instanceId);
        try {
            boolean done = false;
            String newToken = null;

            while (!done) {
                DescribeAlarmsResponse response;
                DescribeAlarmsRequest request;
                if (newToken == null) {
                    request = DescribeAlarmsRequest.builder().build();
                } else {
                    request = DescribeAlarmsRequest.builder()
                            .nextToken(newToken)
                            .build();
                }
                response = client.describeAlarms(request);
                for (MetricAlarm alarm : response.metricAlarms()) {
                    if (alarm.alarmName().equals(alarmName)) {
                        return true;
                    }
                }
                if (response.nextToken() == null) {
                    done = true;
                } else {
                    newToken = response.nextToken();
                }
            }
        } catch (CloudWatchException e) {
            logger.error("Error querying CloudWatch for alarm with name: {}", ALARM_NAME_BASIC_CPU_UTIL);
        }
        return false;
    }
}
