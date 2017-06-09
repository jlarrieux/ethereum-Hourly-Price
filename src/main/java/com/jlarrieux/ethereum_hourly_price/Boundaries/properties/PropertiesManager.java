package com.jlarrieux.ethereum_hourly_price.Boundaries.properties;



import java.io.*;
import java.util.Properties;



/**
 * Created by Jeannius on 6/5/2017.
 */
public class PropertiesManager {

    public static void main(String[] args) throws IOException {
//        PropertiesManager.writeProp(Constants.combineName(Constants.CRYPTO,Constants.CONSUMER_SECRET), "");

//        System.out.println(PropertiesManager.readPropertiesValue("developer"));
//        ClassLoader classLoader = new PropertiesManager().getClass().getClassLoader();
//        FileInputStream inputStream = (FileInputStream) Thread.currentThread().getContextClassLoader().getResourceAsStream("/tone/token.properties");
//        System.out.println(file.exists());
        Properties properties = PropertiesManager.loadPrevious();
        System.out.println(LOCATION);
        System.out.println(properties.getProperty("redeere"));

    }

    public static final String LOCATION =  "/secret/token.properties";

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



    public static Properties loadPrevious(){
        try {
            InputStream in =  PropertiesManager.class.getClass().getResourceAsStream(LOCATION);
            Properties properties = new Properties();
                if (in!=null){
                    System.out.println("null3");
                properties.load(in);
                in.close();
                return properties;
            }

            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String readPropertiesValue(String key){
        Properties prop = loadPrevious();
        System.out.println();
        return prop.getProperty(key);
    }


}
