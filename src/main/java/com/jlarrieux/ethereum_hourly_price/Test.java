package com.jlarrieux.ethereum_hourly_price;



import com.jlarrieux.ethereum_hourly_price.Boundaries.REST.CoinMarketCapRestClient;

import java.io.IOException;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class Test {


    public static void main(String[] args) throws IOException{
        CoinMarketCapRestClient client = new CoinMarketCapRestClient();



//        Client client = Client.create();
//
//        WebResource webResource = client.resource("http://coinmarketcap.northpole.ro/api/eth.json");
//
//        ClientResponse response = webResource.accept("application/json")
//                                                .get(ClientResponse.class);
//        if(response.getStatus()!=200) throw new RuntimeException("Failed: HTTP error code jlarrieux: "+ response.getStatus());
//        else {
//            String output = response.getEntity(String.class);
//
//            System.out.println("Output from Server .... \n");
//            System.out.println(output);
//        }
//        client.destroy();


    }


}
