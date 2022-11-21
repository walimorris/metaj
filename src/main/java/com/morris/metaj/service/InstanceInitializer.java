package com.morris.metaj.service;

import com.morris.metaj.model.MetaInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InstanceInitializer {
    private static final Logger logger = LoggerFactory.getLogger(InstanceInitializer.class);

    @Autowired
    CommandExecutor commandExecutor;

    @Autowired
    InstanceCommands commands;

    public InstanceInitializer() {}

    public MetaInstance initMetaInstance() throws IOException {
        return new MetaInstance(
                commandExecutor.execute(commands.instanceCommand()),
                commandExecutor.execute(commands.publicHostnameCommand()),
                commandExecutor.execute(commands.availabilityZoneCommand()),
                commandExecutor.execute(commands.instanceAmiIdCommand()),
                commandExecutor.execute(commands.instanceAmiLaunchIndexCommand()),
                commandExecutor.execute(commands.instanceAmiManifestPathCommand()),
                commandExecutor.execute(commands.instanceLocalIpv4()),
                commandExecutor.execute(commands.instanceType()),
                commandExecutor.execute(commands.instanceKernelId()),
                commandExecutor.execute(commands.instancePublicKeys())
        );
    }
}

