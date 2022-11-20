package com.morris.metaj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InstanceCommands {
    private static final Logger logger = LoggerFactory.getLogger(InstanceCommands.class);

    public InstanceCommands() {}

    private static final String EC2_METADATA = "ec2-metadata";

    public String[] instanceCommand() {
        return new String[]{EC2_METADATA, "-i"};
    }

    public String[] publicHostnameCommand() {
        return new String[]{EC2_METADATA, "-p"};
    }

    public String[] availabilityZoneCommand() {
        return new String[]{EC2_METADATA, "-z"};
    }

    public String[] instanceAmiIdCommand() {
        return new String[]{EC2_METADATA, "-a"};
    }

    public String[] instanceAmiLaunchIndexCommand() {
        return new String[]{EC2_METADATA, "-l"};
    }

    public String[] instanceAmiManifestPathCommand() {
        return new String[]{EC2_METADATA, "-m"};
    }
}
