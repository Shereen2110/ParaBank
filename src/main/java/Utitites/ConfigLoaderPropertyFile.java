package Utitites;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoaderPropertyFile {
    private Properties properties;
    public ConfigLoaderPropertyFile(String filepath)
    {
        loadProperties(filepath);
    }
    private void loadProperties(String filepath)
    {
        properties=new Properties();
        try {
            FileInputStream fileInputStream=new FileInputStream(filepath);
            properties.load(fileInputStream);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getContent(String key)
    {
        return properties.getProperty(key);// to read from this file
    }
}
