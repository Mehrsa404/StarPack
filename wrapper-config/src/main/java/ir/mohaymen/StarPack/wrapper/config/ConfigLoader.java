package ir.mohaymen.starpack.wrapper.config;


public class ConfigLoader {

    public Boolean getBoolean(Boolean key, Boolean defaultValue) {
        if(key == null){
            return defaultValue;
        }
        return key;
    }

    public String getString(String key, String defaultValue) {
        if(key == null){
            return defaultValue;
        }
        return key;
    }

    public int getInt(Integer key, int defaultValue) {
        if(key == null){
            return defaultValue;
        }
        return key;
    }

}