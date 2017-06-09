package com.jlarrieux.ethereum_hourly_price.TwitterAccessor;



import com.jlarrieux.ethereum_hourly_price.other.Constants;
import com.jlarrieux.ethereum_hourly_price.Boundaries.Entity.Coin;
import twitter4j.TwitterException;

import java.io.IOException;



/**
 * Created by Jeannius on 6/7/2017.
 */
public abstract class AbstractCoinManager extends AbstractTwitterAccessor {


    protected Coin coin;


    public AbstractCoinManager() throws IOException, TwitterException {
        super();
        coin = new Coin(name);
    }



    protected String getStatusString(){
        double price = coin.getPrice();
        double marketcap = coin.getMarketCap();
        double volume = coin.getVolume24();
        double percent = coin.getPercentChange1Hour();
        return String.format(coin.getSTATUS_TEMPLATE(),name, Constants.currencyParse(price), qualifer(percent), percent , Constants.currencyParse(marketcap), Constants.currencyParse(volume));
    }
}
