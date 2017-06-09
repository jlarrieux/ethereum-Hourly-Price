package com.jlarrieux.ethereum_hourly_price.Boundaries;



import com.jlarrieux.ethereum_hourly_price.Boundaries.REST.CoinMarketCapRestClient;
import com.jlarrieux.ethereum_hourly_price.TwitterAccessor.EthereumTwitterBot;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



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


    @Scheduled(fixedRate = 500)
    public void test(){
        try {
            ClassPathResource resource = new ClassPathResource("secret/token.properties");
            InputStream inputStream = resource.getInputStream();

            Properties properties = new Properties();
            properties.load(inputStream);
            System.out.println(properties.getProperty("red"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
