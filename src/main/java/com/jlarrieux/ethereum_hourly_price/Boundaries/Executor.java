package com.jlarrieux.ethereum_hourly_price.Boundaries;



import com.jlarrieux.ethereum_hourly_price.Boundaries.REST.CoinMarketCapRestClient;
import com.jlarrieux.ethereum_hourly_price.TwitterAccessor.CryptoTwitterBot;
import com.jlarrieux.ethereum_hourly_price.TwitterAccessor.EthereumTwitterBot;
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

        EthereumTwitterBot twitterBot = new EthereumTwitterBot();
        CoinMarketCapRestClient ethereumClient = new CoinMarketCapRestClient();
//        twitterBot.updateStatus(ethereumClient.composeMessage("ethereum"));
        System.out.println("executed!");

    }


    @Scheduled(fixedRate = 1000)
    public void test() throws IOException, TwitterException {
        CryptoTwitterBot twitterBot = new CryptoTwitterBot();
       // twitterBot.updateStatus();
    }

}
