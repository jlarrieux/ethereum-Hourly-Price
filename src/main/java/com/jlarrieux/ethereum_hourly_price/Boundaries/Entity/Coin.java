package com.jlarrieux.ethereum_hourly_price.Boundaries.Entity;



import com.google.gson.JsonObject;
import com.jlarrieux.ethereum_hourly_price.Boundaries.Constants;
import com.jlarrieux.ethereum_hourly_price.Boundaries.REST.CoinMarketCapRestClient;



/**
 * Created by Jeannius on 6/7/2017.
 */
public class Coin {
    JsonObject jsonObject;
    private final String PRICE_STRING="price_usd";
    private final String VOLUME24_STRING="24h_volume_usd";
    private final String MARKET_CAP_STRING="market_cap_usd";
    private final String PERCENT_1HOUR_CHANGE_STRING="percent_change_1h";
    private final String STATUS_TEMPLATE ="Current #%s price is %s, %s%.2f%%, a market cap of %s and 24 hr. vol. of %s";
    private String name;



    public Coin(String name){
        this.name =name;
        jsonObject = CoinMarketCapRestClient.getCoin(this.name);

    }



    public double getPrice() {
        return Constants.getDoubleFromJsonObject(jsonObject,PRICE_STRING);
    }



    public double getVolume24() {
        return Constants.getDoubleFromJsonObject(jsonObject,VOLUME24_STRING);
    }



    public double getMarketCap() {
        return Constants.getDoubleFromJsonObject(jsonObject,MARKET_CAP_STRING);
    }



    public double getPercentChange1Hour() {
        return Constants.getDoubleFromJsonObject(jsonObject,PERCENT_1HOUR_CHANGE_STRING);
    }


    public String getSTATUS_TEMPLATE(){
        return STATUS_TEMPLATE;
    }
}
