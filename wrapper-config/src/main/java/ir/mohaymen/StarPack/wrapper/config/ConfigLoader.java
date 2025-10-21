package ir.mohaymen.starpack.wrapper.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream("application.yml")) {
            if (in == null) {
                throw new RuntimeException("application.yml not found in classpath");
            }

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Map<String, Object> yamlMap = mapper.readValue(in, Map.class);

            flattenMapToProperties("", yamlMap, props);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.yml", e);
        }
    }

    public static String getString(String key) {
        return props.getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(props.getProperty(key, Boolean.toString(defaultValue)));
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

    private static void flattenMapToProperties(String prefix, Map<String, Object> map, Properties props) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                flattenMapToProperties(key, (Map<String, Object>) value, props); // برای داده‌های تو در تو
            } else {
                props.setProperty(key, value.toString());
            }
        }
    }
}
