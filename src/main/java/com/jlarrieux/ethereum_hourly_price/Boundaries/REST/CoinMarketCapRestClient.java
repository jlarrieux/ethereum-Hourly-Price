package com.jlarrieux.ethereum_hourly_price.Boundaries.REST;



import com.google.gson.JsonObject;

import java.io.*;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class CoinMarketCapRestClient extends RestClient implements Serializable{

    double lastPrice, currentPrice=0;
    private static final String URI= "http://coinmarketcap.northpole.ro/api/%s.json";
    private JsonObject jsonObject;
    private String priceFile = "lasrPrice.info";


    private void parse(String symbole){
        jsonObject= getJsonObjectFromJsonString(getJsonAsString(String.format(URI,symbole)));


    }

    public double getCurrentPrice(String symbol) throws IOException {
        parse(symbol);
        readLastPrice();
        currentPrice =    getDoubleFromJsonObject(jsonObject,"price");
        return currentPrice;
    }




    public double calculatePercentage(){
        return (currentPrice - lastPrice)*100/currentPrice;
    }

    public String composeMessage(String symbol) throws IOException {
        getCurrentPrice(symbol);
        double percentDiff = calculatePercentage();
        writeLastPrice();
        return String.format("The current price of Ethereum is $%.2f. Ethereum has %s %.2f %%",currentPrice, qualifer(percentDiff), percentDiff);
    }


    private String qualifer(double difference){
       return difference<0? "decreased by -": "increased by +";
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
