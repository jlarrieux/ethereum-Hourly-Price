package com.jlarrieux.ethereum_hourly_price.Boundaries.Entity;



import com.google.gson.JsonObject;
import com.jlarrieux.ethereum_hourly_price.other.Constants;



/**
 * Created by Jeannius on 6/9/2017.
 */
public class Global {
    JsonObject jsonObject;
    private final String TOTAL_MARKET_CAP = "total_market_cap_usd";
    private final String _24_HOUR_VOLUME ="total_24h_volume_usd";
    private final String GLOBAL_STATUS_TEMPLATE="Total #CryptoCurrency Market Cap is %s, with total 24 hr. volume of %s";


    public Global(JsonObject object){
        jsonObject = object;
    }


    public double getTotalMarketCap(){
        return Constants.getDoubleFromJsonObject(jsonObject,TOTAL_MARKET_CAP);
    }


    public double get24HourVolume(){
        return Constants.getDoubleFromJsonObject(jsonObject, _24_HOUR_VOLUME);
    }



    public String getGLOBAL_STATUS_TEMPLATE() {
        return GLOBAL_STATUS_TEMPLATE;
    }
}
