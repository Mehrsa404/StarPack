package ir.mohaymen.starpack.wrapper.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in == null) {
                throw new RuntimeException("application.properties not found in classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    public static String getString(String key) {
        return props.getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        String val = props.getProperty(key);
        return (val != null && !val.isBlank()) ? val : defaultValue;
    }

    public static boolean getBoolean(String key) {
        String val = props.getProperty(key);
        return val != null && Boolean.parseBoolean(val.trim());
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String val = props.getProperty(key);
        return (val != null && !val.isBlank()) ? Boolean.parseBoolean(val.trim()) : defaultValue;
    }

    public static int getInt(String key) {
        String val = props.getProperty(key);
        if (val == null || val.isBlank()) {
            throw new IllegalArgumentException("Missing required integer property: " + key);
        }
        return Integer.parseInt(val.trim());
    }

    public static int getInt(String key, int defaultValue) {
        String val = props.getProperty(key);
        return (val != null && !val.isBlank()) ? Integer.parseInt(val.trim()) : defaultValue;
    }

    public static List<Integer> getIntList(String key) {
        String raw = props.getProperty(key);
        if (raw == null || raw.isBlank()) {
            return List.of();
        }
        return Arrays.stream(raw.split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .toList();
    }

}