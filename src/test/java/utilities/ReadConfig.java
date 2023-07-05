package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    Properties properties;

    public ReadConfig(){
        File src = new File("Configuration/config.properties");

        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            properties = new Properties();
            properties.load(fileInputStream);

        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de configuración: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getAppURL(){
        String url = properties.getProperty("baseURL");
        return url;
    }

    public String getUsername(){
        String username = properties.getProperty("username");
        return username;
    }

    public String getPassword(){
        String password = properties.getProperty("password");
        return password;
    }

    public String getChromePath(){
        return properties.getProperty("chromepath");
    }

    public String getFirefoxPath(){
        return properties.getProperty("firefoxpath");
    }
}
