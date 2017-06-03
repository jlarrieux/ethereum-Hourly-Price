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
public class AbstractTwitterAccessor implements Serializable{

    protected Twitter twitter;
    protected AccessToken aT;
    private long aTID;
    private String fileName = "myTwitterDetails.info";

    public AbstractTwitterAccessor() throws IOException, TwitterException {
        File file = new File(fileName);
        if(file.exists()) readObject();
        else getTwitterAccess();

    }

    private  void getTwitterAccess() throws IOException, TwitterException {
// The factory instance is re-useable and thread safe.
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("qMZDFk10229IbUsrM5r5IlzQu", "6pPU6pUeqwbb9ivBg9a8O3tueliidSDz9mT6BjUnYLjy77wBCp");
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

        aTID = twitter.verifyCredentials().getId();
        writeObject();
//System.exit(0);
    }


    private  void readObject() {
        try {
            FileInputStream fileIn =
                    new FileInputStream(fileName);
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
        out.close();
        fileOut.close();

    }

}
