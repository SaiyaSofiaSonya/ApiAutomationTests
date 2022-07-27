package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class Endpoints {
    //public static String ENDPOINT_POST = "http://jsonplaceholder.typicode.com";
    public final static String ENDPOINT_POST = getUrl();
    public static String getUrl() {
        File src = new File(".\\src\\main\\resources\\config.properties");
        String endpoint = null;
        try {
            FileInputStream fis = new FileInputStream(src);
            Properties pro = new Properties();
            pro.load(fis);
            endpoint = pro.getProperty("api.uri");
            System.out.println("------------" + endpoint);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return endpoint;
    }
}
