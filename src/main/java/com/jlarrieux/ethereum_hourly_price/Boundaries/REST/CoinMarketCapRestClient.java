package com.jlarrieux.ethereum_hourly_price.Boundaries.REST;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jlarrieux.ethereum_hourly_price.Boundaries.Constants;

import java.io.Serializable;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class CoinMarketCapRestClient extends RestClient implements Serializable{

    double lastPrice, currentPrice=0;

    private JsonObject jsonObject;
    private String priceFile = "lastPrice.ether";



    public static JsonObject getCoin(String symbol){
        return  (JsonObject) getArray(symbol, Constants.URI_COIN).get(0);
    }

    public static JsonArray getArray(String symbol, String URI){
        String json = getJsonAsString(String.format(URI,symbol));
        return Constants.getJsonArrayFromJsonString(json);
    }



















}
