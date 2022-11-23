package com.morris.metaj.service;

import java.io.IOException;

/**
 * {@code com/morris/metaj/service/CommandExecutor.java} exposes a set of methods for executing CLI commands.
 */
public interface CommandExecutor {

    /**
     * Executes a given command and returns the command output.
     *
     * @param command executable command
     *
     * @return {@link String}
     * @throws IOException throws IOException
     */
    String execute(String[] command) throws IOException;
}
