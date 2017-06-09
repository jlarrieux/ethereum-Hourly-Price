package com.jlarrieux.ethereum_hourly_price.Boundaries.properties;



import com.jlarrieux.ethereum_hourly_price.Boundaries.Constants;

import java.io.*;
import java.util.Properties;



/**
 * Created by Jeannius on 6/5/2017.
 */
public class PropertiesManager {

    public static void main(String[] args){
        PropertiesManager.writeProp(Constants.combineName(Constants.CRYPTO,Constants.CONSUMER_SECRET), "");
//        Properties prop = PropertiesManager.readProperties();
//        System.out.println(prop.getProperty("developer"));
//        ClassLoader classLoader = new PropertiesManager().getClass().getClassLoader();
//        File file = new File(classLoader.getResource("config/sample.txt").getFile());
//        System.out.println(file.exists());

    }

    public static final String LOCATION = Constants.SRC_MAIN_RESOURCES + "secret/token.properties";

    public static void writeProp(String key, String value){
        File file = new File(LOCATION);

        Properties properties =loadPrevious() ;
        properties.setProperty(key, value);
        System.out.println(file.getAbsolutePath());

        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            properties.store(outputStream, "testing this");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private static Properties loadPrevious(){
        try {
            FileInputStream in = new FileInputStream(LOCATION);
            Properties properties = new Properties();
            properties.load(in);
            in.close();
            return properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String readPropertiesValue(String key){
        Properties prop = loadPrevious();
        return prop.getProperty(key);
    }


}
