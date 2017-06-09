package com.jlarrieux.ethereum_hourly_price.TwitterAccessor;



import com.jlarrieux.ethereum_hourly_price.Boundaries.Entity.Global;
import com.jlarrieux.ethereum_hourly_price.Boundaries.REST.CoinMarketCapRestClient;
import com.jlarrieux.ethereum_hourly_price.Boundaries.properties.PropertiesManager;
import com.jlarrieux.ethereum_hourly_price.other.Constants;
import twitter4j.TwitterException;

import java.io.IOException;



/**
 * Created by Jeannius on 6/7/2017.
 */
public class CryptoTwitterBot extends AbstractTwitterAccessor {

    Global global;

    public CryptoTwitterBot() throws IOException, TwitterException {
        super();
    }



    @Override
    protected String getConsumerKey() {
         return PropertiesManager.readPropertiesValue(Constants.combineName(name, Constants.CONSUMER_KEY));

    }



    @Override
    protected String getConsumerSecret() {
        return PropertiesManager.readPropertiesValue(Constants.combineName(name, Constants.CONSUMER_SECRET));

    }



    @Override
    protected void setName() {
        name = Constants.CRYPTO;
    }








    @Override
    public void updateStatus() throws TwitterException, IOException {
        global = new Global( CoinMarketCapRestClient.getGlobal());

        tweetGlobalStatus();
    }

    private void tweetGlobalStatus() throws TwitterException{
        double marketcap = global.getTotalMarketCap();
        double _24hourVolume = global.get24HourVolume();
        twitter.updateStatus(String.format(global.getGLOBAL_STATUS_TEMPLATE(),Constants.currencyParse(marketcap),Constants.currencyParse( _24hourVolume)));
    }

    public static void main(String[] args) throws IOException, TwitterException {
        CryptoTwitterBot twitterBot = new CryptoTwitterBot();
        twitterBot.updateStatus();
    }


}
