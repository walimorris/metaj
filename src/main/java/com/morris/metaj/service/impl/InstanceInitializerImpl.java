package com.morris.metaj.service.impl;

import com.morris.metaj.model.MetaInstance;
import com.morris.metaj.service.InstanceInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InstanceInitializerImpl implements InstanceInitializer {
    private static final Logger logger = LoggerFactory.getLogger(InstanceInitializerImpl.class);

    @Autowired
    CommandExecutorImpl commandExecutor;

    @Autowired
    InstanceCommandsImpl commands;

    public InstanceInitializerImpl() {}

    @Override
    public MetaInstance initMetaInstance() throws IOException {
        return new MetaInstance(
                commandExecutor.execute(commands.instanceCommand()),
                commandExecutor.execute(commands.publicHostnameCommand()),
                commandExecutor.execute(commands.localHostnameCommand()),
                commandExecutor.execute(commands.availabilityZoneCommand()),
                commandExecutor.execute(commands.instanceAmiIdCommand()),
                commandExecutor.execute(commands.instanceAmiLaunchIndexCommand()),
                commandExecutor.execute(commands.instanceAmiManifestPathCommand()),
                commandExecutor.execute(commands.instanceLocalIpv4Command()),
                commandExecutor.execute(commands.instanceTypeCommand()),
                commandExecutor.execute(commands.instanceKernelIdCommand()),
                commandExecutor.execute(commands.instancePublicKeysCommand()),
                commandExecutor.execute(commands.instanceAncestorAmiIdsCommand()),
                commandExecutor.execute(commands.instanceBlockDeviceMappingCommand()),
                commandExecutor.execute(commands.instanceProductCodesCommand())
        );
    }
}

