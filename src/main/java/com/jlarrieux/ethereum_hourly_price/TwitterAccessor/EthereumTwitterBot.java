package com.jlarrieux.ethereum_hourly_price.TwitterAccessor;



import com.jlarrieux.ethereum_hourly_price.Boundaries.Constants;
import com.jlarrieux.ethereum_hourly_price.Boundaries.properties.PropertiesManager;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

import java.io.IOException;



/**
 * Created by Jeannius on 6/1/2017.
 */
public class EthereumTwitterBot extends AbstractCoinManager {



    public EthereumTwitterBot() throws IOException, TwitterException {
        super();
    }



    @Override
    protected String getConsumerKey() {
        return PropertiesManager.readPropertiesValue(Constants.combineName(name,Constants.CONSUMER_KEY));
    }



    @Override
    protected String getConsumerSecret() {
        return PropertiesManager.readPropertiesValue(Constants.combineName(name,Constants.CONSUMER_SECRET));
    }




    @Override
    protected void setName() {
        name = Constants.ETHEREUM;
    }



    @Override
    public void updateStatus() throws TwitterException, IOException {
        StatusUpdate su = new StatusUpdate(getStatusString());
        Status status = twitter.updateStatus(su);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");

    }



    public static void main(String[] args) throws IOException, TwitterException {
        EthereumTwitterBot twitterBot = new EthereumTwitterBot();
        twitterBot.updateStatus();
    }




}
