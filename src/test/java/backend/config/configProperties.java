package backend.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class configProperties {

    public static Properties prop = new Properties();

    public static String readPropertyFile(String propertyName)
    {
        try
        {
            InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
            prop.load(input);

            return prop.getProperty(propertyName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }

}
