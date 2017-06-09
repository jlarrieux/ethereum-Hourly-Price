package com.jlarrieux.ethereum_hourly_price.Boundaries.REST;



import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



/**
 * Created by Jeannius on 6/2/2017.
 */
public class RestClient {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    protected static String getJsonAsString(String url){
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept("application/json")
                                            .get(ClientResponse.class);
        client.destroy();

        return handleResponse(response);

    }


    private static String handleResponse(ClientResponse response){
        if(response.getStatus()!=200) throw  new RuntimeException("Failed: HTTP error code: "+ response.getStatus());
        else {
            return response.getEntity(String.class);
        }
    }


}
