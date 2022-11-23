package com.morris.metaj.service.impl;

import com.morris.metaj.service.CommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Service
public class CommandExecutorImpl implements CommandExecutor {
    private static final Logger logger = LoggerFactory.getLogger(CommandExecutorImpl.class);

    @Autowired
    InstanceCommandsImpl commands;

    public CommandExecutorImpl() {}

    @Override
    public String execute(String[] command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        StringBuilder result = new StringBuilder();
        String s;

        while ((s = stdInput.readLine()) != null) {
            result.append(s);
        }

        while ((s = stdError.readLine()) != null) {
            result.append(s);
        }
        stdInput.close();
        stdError.close();
        return result.toString();
    }

    public String parseHostname()  {
        try {
            String host = execute(commands.publicHostnameCommand());
            host = host.split("\\.")[0];
            return host.substring(18).trim();
        } catch (IOException e) {
            logger.error("Error executing command: {}", Arrays.toString(commands.publicHostnameCommand()));
        }
        return "http://localhost:8080";
    }
}
