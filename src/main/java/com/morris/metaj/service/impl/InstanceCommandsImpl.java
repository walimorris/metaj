package com.morris.metaj.service.impl;

import com.morris.metaj.service.InstanceCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InstanceCommandsImpl implements InstanceCommands {
    private static final Logger logger = LoggerFactory.getLogger(InstanceCommandsImpl.class);

    public InstanceCommandsImpl() {}

    private static final String EC2_METADATA = "ec2-metadata";

    @Override
    public String[] instanceCommand() {
        return new String[]{EC2_METADATA, "-i"};
    }

    @Override
    public String[] publicHostnameCommand() {
        return new String[]{EC2_METADATA, "-p"};
    }

    @Override
    public String[] localHostnameCommand() {
        return new String[]{EC2_METADATA, "-h"};
    }

    @Override
    public String[] availabilityZoneCommand() {
        return new String[]{EC2_METADATA, "-z"};
    }

    @Override
    public String[] instanceAmiIdCommand() {
        return new String[]{EC2_METADATA, "-a"};
    }

    @Override
    public String[] instanceAmiLaunchIndexCommand() {
        return new String[]{EC2_METADATA, "-l"};
    }

    @Override
    public String[] instanceAmiManifestPathCommand() {
        return new String[]{EC2_METADATA, "-m"};
    }

    @Override
    public String[] instanceTypeCommand() {
        return new String[]{EC2_METADATA, "-t"};
    }

    @Override
    public String[] instanceLocalIpv4Command() {
        return new String[]{EC2_METADATA, "-o"};
    }

    @Override
    public String[] instanceKernelIdCommand() {
        return new String[]{EC2_METADATA, "-k"};
    }

    @Override
    public String[] instancePublicKeysCommand() {
        return new String[]{EC2_METADATA, "-u"};
    }

    @Override
    public String[] instanceAncestorAmiIdsCommand() {
        return new String[]{EC2_METADATA, "-n"};
    }

    @Override
    public String[] instanceBlockDeviceMappingCommand() {
        return new String[]{EC2_METADATA, "-b"};
    }

    @Override
    public String[] instanceProductCodesCommand() {
        return new String[]{EC2_METADATA, "-c"};
    }
}
