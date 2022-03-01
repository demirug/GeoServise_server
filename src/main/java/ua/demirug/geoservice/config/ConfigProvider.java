package ua.demirug.geoservice.config;

import org.yaml.snakeyaml.Yaml;
import ua.demirug.geoservice.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class ConfigProvider extends ConfigFile {

    private static ConfigProvider configFile;

    private ConfigProvider() {}

    private Map<String, Object> data;

    public void load() {
        try {
            File file = this.file;
            this.data = new Yaml().load(new FileInputStream(file));
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        Log.info("Config file loaded");
    }

    public String getString(String path) {
        return (String) data.get(path);
    }

    public Integer getInteger(String path) {
        return (Integer) data.get(path);
    }

    public Boolean getBoolean(String path) {
        return (Boolean) data.get(path);
    }

    public Float getFloat(String path) {
        return (Float) data.get(path);
    }

    public Object getObject(String path) {
        return data.get(path);
    }


    public static ConfigProvider getInstance() {
        if (ConfigProvider.configFile == null) {
            ConfigProvider.configFile = new ConfigProvider();
        }
        return ConfigProvider.configFile;
    }

}
