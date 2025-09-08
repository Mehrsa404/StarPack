package ir.mohaymen.StarPack.wrapper.config;

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

    public static String getBoolean(String key) {
        return Boolean.valueOf(props.getProperty(key)).toString();
    }

    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    public static int getInt(String key, int defaultValue) {
        String val = props.getProperty(key);
        return (val != null) ? Integer.parseInt(val) : defaultValue;
    }

    public static List<Integer> getIntList(String key) {
        String raw = props.getProperty(key);
        if (raw == null) {
            return List.of();
        }
        if (raw == null || raw.isBlank()) {
            return List.of();
        }
        return Arrays.stream(raw.split(",")).map(String::trim).map(Integer::parseInt).toList();
    }
}