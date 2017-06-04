package com.jlarrieux.ethereum_hourly_price.Boundaries;



import com.jlarrieux.ethereum_hourly_price.Boundaries.REST.CoinMarketCapRestClient;
import com.jlarrieux.ethereum_hourly_price.TwitterBot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

import java.io.IOException;



/**
 * Created by Jeannius on 6/3/2017.
 */
@Component
public class Executor {


    @Scheduled(cron="0 0 * * * *")
    public void sendTweet() throws IOException, TwitterException {
                    TwitterBot twitterBot = new TwitterBot();

        CoinMarketCapRestClient ethereumClient = new CoinMarketCapRestClient();
            twitterBot.updateStatus(ethereumClient.composeMessage("ethereum"));
            System.out.println("executed!");

    }


}
