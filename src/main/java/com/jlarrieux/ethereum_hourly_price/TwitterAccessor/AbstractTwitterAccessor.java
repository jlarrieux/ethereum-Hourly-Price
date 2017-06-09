package com.jlarrieux.ethereum_hourly_price.TwitterAccessor;



import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.*;



/**
 * Created by Jeannius on 6/1/2017.
 */
public abstract class AbstractTwitterAccessor implements Serializable{

    protected Twitter twitter;
    protected AccessToken aT;
    private long aTID;
    protected String fileName;
    protected String  name;



    public AbstractTwitterAccessor() throws IOException, TwitterException {
        setName();
        fileName = "/token/"+name+".info";

        InputStream in = getClass().getResourceAsStream(fileName);
        System.out.println(in==null);

        if(in!=null) readObject();
        else getTwitterAccess();

    }

    private File getTempFile(){

        return null;
    }

    protected abstract String  getConsumerKey();

    protected abstract String getConsumerSecret();


    private  void getTwitterAccess() throws IOException, TwitterException {
// The factory instance is re-useable and thread safe.
        twitter = new TwitterFactory().getInstance();
        boolean writeFlag = false;

        twitter.setOAuthConsumer(getConsumerKey(), getConsumerSecret());
        RequestToken requestToken = twitter.getOAuthRequestToken();
        aT = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == aT) {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try {
                if (pin.length() > 0) {
                    aT = twitter.getOAuthAccessToken(requestToken, pin);
                } else {
                    aT = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    System.out.println("Unable to get the access token.");
                } else {
                    te.printStackTrace();
                }
            }

        }
//        System.out.println("Access Token to string: "+ aT.toString());
        aTID = twitter.verifyCredentials().getId();
//        writeObject();
//        PropertiesManager.writeProp(Constants.combineName(name,Constants.ACCESS_TOKEN), aT);
    }


    private  void readObject() {
        try {
            System.out.println("Written to file: "+fileName);
            InputStream fileIn =getClass().getResourceAsStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
             AbstractTwitterAccessor dftb = (AbstractTwitterAccessor) in.readObject();
             aT = dftb.aT;
             aTID = dftb.aTID;
             twitter = dftb.twitter;
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println(" DFTB class not found");
            c.printStackTrace();

        }

        System.out.println("Deserialized Access...");
        System.out.println("Access Token ID: " + aTID);
        System.out.println("Access Token: " + aT.toString());
        System.out.println("Tweitter: " + twitter.toString());


    }



    private  void writeObject() throws IOException {
        FileOutputStream fileOut =
                new FileOutputStream(fileName);
        ObjectOutputStream out =
                new ObjectOutputStream(fileOut);
        System.out.println("Serialising:...");
        System.out.println("Access Token ID: " + aTID);
        System.out.println("Access Token: " + aT.toString());
        System.out.println("Tweitter: " + twitter.toString());
        out.writeObject(this);
        System.out.println("Written to file: "+fileName);
        out.close();

        fileOut.close();


    }

    protected  abstract void setName();
    protected abstract String getStatusString();
    public abstract void updateStatus() throws TwitterException,IOException;

    protected String qualifer(double difference){
        return difference<0? "a decrease of ": "an increase of +";
    }


}
