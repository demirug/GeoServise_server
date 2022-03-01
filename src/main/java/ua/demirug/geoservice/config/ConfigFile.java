package ua.demirug.geoservice.config;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfigFile {

    public File file;

    public ConfigFile() {
        this.initFile();
    }

    private File initFile() {
        File file = new File("config.yml");
        if(!file.exists()) {
            URL input = getClass().getResource("/config.yml");
            try {
                FileUtils.copyURLToFile(input, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.file = file;
    }

}
