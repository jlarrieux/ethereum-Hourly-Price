package com.jlarrieux.ethereum_hourly_price.Boundaries.REST;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class CoinMarketCapRestClient extends RestClient implements Serializable{

    double lastPrice, currentPrice=0;
    private static final String URI= "http://coinmarketcap.northpole.ro/api/%s.json";
    private static final String URI2 ="https://api.coinmarketcap.com/v1/ticker/%s";
    private JsonObject jsonObject;
    private String priceFile = "lastPrice.ether";


    public static void main(String[] args) throws IOException {
        CoinMarketCapRestClient client = new CoinMarketCapRestClient();
        System.out.println(client.composeMessage("ethereum"));

    }


//    private void parse(String symbole){
//        jsonObject= getJsonObjectFromJsonString(getJsonAsString(String.format(URI,symbole)));
//    }

    private double parse(String currency){
        String json = getJsonAsString(String.format(URI2,currency));
        JsonArray array = getJsonArrayFromJsonString(json);
        jsonObject = (JsonObject) array.get(0);
        return getDoubleFromJsonObject(jsonObject, "price_usd");

    }

    public double getCurrentPrice(String symbol) throws IOException {
        parse(symbol);
        readLastPrice();
        currentPrice =    getDoubleFromJsonObject(jsonObject,"price_usd");
        return currentPrice;
    }

    private double getMarketCap(){
        return getDoubleFromJsonObject(jsonObject,"market_cap_usd");
    }

    private double get24HourVolume(){
        return getDoubleFromJsonObject(jsonObject, "24h_volume_usd");
    }


    private String currencyParse(double amount){
        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setMinimumFractionDigits(0);
        return numberFormat.format(amount);
    }

    public double calculatePercentage(){
        return (currentPrice - lastPrice)*100/currentPrice;
    }

    public String composeMessage(String symbol) throws IOException {
        getCurrentPrice(symbol);
        double percentDiff = calculatePercentage();
        writeLastPrice();
        String conditional = lastPrice==0?"":String.format("%s%.2f%%, ", qualifer(percentDiff), percentDiff);
        return String.format("Current #Ethereum price is $%.2f, %s market cap of  %s and 24 hr vol. of %s"
                ,currentPrice, conditional, currencyParse(getMarketCap()), currencyParse(get24HourVolume()));
    }


    private String qualifer(double difference){
       return difference<0? "a decreased of ": "an increased of +";
    }



    private void writeLastPrice() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(priceFile);
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeDouble(currentPrice);
        out.close();
        fileOutputStream.close();
    }

    private void readLastPrice() throws IOException{
        File file = new File(priceFile);

        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(priceFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            lastPrice = inputStream.readDouble();
            inputStream.close();
            fileInputStream.close();
        } else lastPrice=0;

    }










}
