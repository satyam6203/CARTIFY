package com.satyam.Ecommerce.Config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvConfig implements EnvironmentPostProcessor {

    private static final String ENV_PROPERTIES_SOURCE = "envProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> envProperties = new HashMap<>();

        try {
            File envFile = new File("src/main/resources/file.env");
            if (!envFile.exists()) {
                envFile = new File("file.env");
            }

            if (envFile.exists()) {
                System.out.println("Loading env file from: " + envFile.getAbsolutePath());
                List<String> lines = Files.readAllLines(envFile.toPath());
                for (String line : lines) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;
                    int eq = line.indexOf('=');
                    if (eq > 0) {
                        String key = line.substring(0, eq).trim();
                        String value = line.substring(eq + 1).trim();
                        envProperties.put(key, value);
                        System.setProperty(key, value);
                    }
                }

                if (!envProperties.isEmpty()) {
                    MapPropertySource propertySource = new MapPropertySource(ENV_PROPERTIES_SOURCE, envProperties);
                    environment.getPropertySources().addFirst(propertySource);
                    System.out.println("Environment variables from file.env loaded successfully!");
                }
            } else {
                System.out.println("No file.env found, using system environment variables.");
            }
        } catch (Exception e) {
            System.err.println("Warning: Could not load file.env: " + e.getMessage());
        }
    }
}
