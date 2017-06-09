package com.jlarrieux.ethereum_hourly_price.TwitterAccessor;



import com.jlarrieux.ethereum_hourly_price.Boundaries.Constants;
import com.jlarrieux.ethereum_hourly_price.Boundaries.properties.PropertiesManager;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

import java.io.IOException;



/**
 * Created by Jeannius on 6/7/2017.
 */
public class CryptoTwitterBot extends AbstractTwitterAccessor {


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
    protected String getStatusString() {
        return "test2";
    }



    @Override
    protected void updateStatus() throws TwitterException, IOException {
        StatusUpdate su = new StatusUpdate(getStatusString());
        Status status = twitter.updateStatus(su);
    }

    public static void main(String[] args) throws IOException, TwitterException {
        CryptoTwitterBot twitterBot = new CryptoTwitterBot();
        twitterBot.updateStatus();
    }


}
