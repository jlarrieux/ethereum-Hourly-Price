package com.jlarrieux.ethereum_hourly_price.Boundaries.REST;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jlarrieux.ethereum_hourly_price.other.Constants;

import java.io.Serializable;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class CoinMarketCapRestClient extends RestClient implements Serializable{



    public static JsonObject getCoin(String symbol){
        return  (JsonObject) getArray(symbol, Constants.URI_COIN).get(0);
    }

    public static JsonArray getArray(String symbol, String URI){
        String json = getJsonAsString(String.format(URI,symbol));
        return Constants.getJsonArrayFromJsonString(json);
    }

    public static JsonObject getGlobal(){
        return Constants.getJsonObjectFromJsonString(getJsonAsString(Constants.URI_GLOBAL));
    }



    public static void main(String[] args){
        System.out.println(CoinMarketCapRestClient.getGlobal());
    }















}
