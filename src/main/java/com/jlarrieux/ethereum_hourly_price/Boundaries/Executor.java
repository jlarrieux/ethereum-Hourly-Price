package com.jlarrieux.ethereum_hourly_price.Boundaries;



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
        new CryptoTwitterBot().updateStatus();
        new EthereumTwitterBot().updateStatus();
    }


//    @Scheduled(fixedRate = 30000)
//    public void test() throws IOException, TwitterException {
//        new CryptoTwitterBot().updateStatus();
//        new EthereumTwitterBot().updateStatus();
//    }

}
