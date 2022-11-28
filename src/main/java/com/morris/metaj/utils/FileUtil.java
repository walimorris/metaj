package com.morris.metaj.utils;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FileUtil {
    public static URL getResource(String file) {
        return FileUtils.class.getResource(file);
    }

    public static InputStream getResourceAsStream(String file) {
        return FileUtils.class.getResourceAsStream(file);
    }

    public static String getResourceAsStringValue(String resourceFile) {
        InputStream resourceStream = getResourceAsStream(resourceFile);

        return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
