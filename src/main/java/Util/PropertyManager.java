package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static final String PROPERTY_FILE_NAME = "config.properties";
    private static ThreadLocal<Properties> properties = new ThreadLocal<>();

    private static Properties getProperties() {
        if(properties.get() == null){
            try{
                loadProperties();
            }catch (IOException e) {
                throw new RuntimeException("Properties file could not be loaded");
            }
        }
        return properties.get();
    }

    public static String getProperty(String propertyKey){
        return getProperties().getProperty(propertyKey);
    }

    private static void loadProperties() throws IOException {
       properties.set(new Properties());
        InputStream inputStream = PropertyManager.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
        properties.get().load(inputStream);
    }

}
