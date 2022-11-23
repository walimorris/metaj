package com.morris.metaj.service;

import com.morris.metaj.model.MetaInstance;

public interface InstanceInitializer {

    /**
     * Initializes an Instance of {@link MetaInstance} with current EC2 instance properties
     * utilizing service classes that use and take advantage of the EC2 meta-data utils.
     *
     * @return {@link MetaInstance}
     *
     * @throws Exception may throw Exception in case of command error when utilizing EC2 meta-data
     * utils commands.
     *
     * @see CommandExecutor
     * @see InstanceCommands
     */
    MetaInstance initMetaInstance() throws Exception;
}
