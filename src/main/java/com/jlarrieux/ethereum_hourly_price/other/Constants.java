package com.jlarrieux.ethereum_hourly_price.other;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.NumberFormat;
import java.util.Locale;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class Constants {


    public static final String ETHEREUM = "ethereum";
    public static final String CONSUMER_KEY = "consumerKey";
    public static final String CONSUMER_SECRET = "consumerSecrey";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String SRC_MAIN_RESOURCES = "src/main/resources/";
    public static final String CRYPTO = "crypto";
    public static final String URI_PREFIX = "https://api.coinmarketcap.com/v1/";
    public static final String URI_COIN = URI_PREFIX+"ticker/%s";
    public static final String URI_GLOBAL = URI_PREFIX +"global";


    public static String combineName(String prefix, String suffix){
        return prefix +"."+suffix;
    }



    public static double getDoubleFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsDouble();
    }



    public static int getIntFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsInt();
    }



    public static JsonObject getJsonObjectFromJsonString(String json) {
        return getJsonElementFromString(json).getAsJsonObject();
    }



    public static JsonArray getJsonArrayFromJsonString(String json){
        return getJsonElementFromString(json).getAsJsonArray();
    }



    public static JsonElement getJsonElementFromString(String json){
        return new JsonParser().parse(json);
    }



    public static String getStringFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsString();
    }

    public static String currencyParse(double amount){
        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setMinimumFractionDigits(0);
        return numberFormat.format(amount);
    }
}
