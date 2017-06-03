package com.jlarrieux.ethereum_hourly_price;



import com.jlarrieux.ethereum_hourly_price.TwitterAccessor.AbstractTwitterAccessor;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.Serializable;



/**
 * Created by Jeannius on 6/1/2017.
 */
public class TwitterBot extends AbstractTwitterAccessor implements Serializable{


    public TwitterBot() throws IOException, TwitterException {
        super();
    }



    public void updateStatus(String update) throws TwitterException, IOException {

        if (update.length() > 0) {
            StatusUpdate su = new StatusUpdate(update);
            Status status = twitter.updateStatus(su);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        }
    }







}
