package com.jlarrieux.ethereum_hourly_price.other;



import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.*;
import java.util.List;



/**
 * Created by Jeannius on 6/1/2017.
 */
public class DFTwitterBot implements Serializable {


    private Twitter twitter;
    private AccessToken aT;
    private long aTID;



    public static void main(String args[]) throws Exception {
        DFTwitterBot dftb = new DFTwitterBot();

        File file = new File("twitterdetails.dftb");
        boolean exists = file.exists();
        if (exists) {
            dftb = readObject(dftb);
        } else if (!exists) {
            getTwitterAccess(dftb);
        }

        menu(dftb);

    }



    private static void menu(DFTwitterBot dftb) throws TwitterException, IOException {
        System.out.println("DF Bot Menu");
        System.out.println("1. Update Status");
        System.out.println("2. View Timeline");
        System.out.println("3. Exit");

        System.out.println("Please enter your choice:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String option = br.readLine();
        int a = Integer.parseInt(option);

        switch (a) {
            case 1:
                updateStatus(dftb);
            case 2:
                viewTimeline(dftb);
            case 3:
                System.out.println("Bye!");
                System.exit(0);
            default:
                menu(dftb);
        }

    }



    private static void viewTimeline(DFTwitterBot dftb) throws TwitterException {
        List<Status> statuses = dftb.twitter.getHomeTimeline();
        System.out.println("Showing friends timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }

    }



    private static DFTwitterBot readObject(DFTwitterBot dftb) {
        try {
            FileInputStream fileIn =
                    new FileInputStream("twitterdetails.dftb");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dftb = (DFTwitterBot) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return dftb;
        } catch (ClassNotFoundException c) {
            System.out.println(" DFTB class not found");
            c.printStackTrace();
            return dftb;
        }

        System.out.println("Deserialized Access...");
        System.out.println("Access Token ID: " + dftb.aTID);
        System.out.println("Access Token: " + dftb.aT.toString());
        System.out.println("Tweitter: " + dftb.twitter.toString());
        return dftb;

    }



    private static void updateStatus(DFTwitterBot dftb) throws TwitterException, IOException {
        String statusUpdate = null;

        System.out.println("Please enter a status:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        statusUpdate = br.readLine();
        if (statusUpdate.length() > 0) {
            StatusUpdate su = new StatusUpdate(statusUpdate);
            Status status = dftb.twitter.updateStatus(su);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } else {
            menu(dftb);
        }
    }



    private static void getTwitterAccess(DFTwitterBot dftb) throws IOException, TwitterException {
// The factory instance is re-useable and thread safe.
        dftb.twitter = new TwitterFactory().getInstance();
        dftb.twitter.setOAuthConsumer("qMZDFk10229IbUsrM5r5IlzQu", "6pPU6pUeqwbb9ivBg9a8O3tueliidSDz9mT6BjUnYLjy77wBCp");
        RequestToken requestToken = dftb.twitter.getOAuthRequestToken();
        dftb.aT = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == dftb.aT) {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try {
                if (pin.length() > 0) {
                    dftb.aT = dftb.twitter.getOAuthAccessToken(requestToken, pin);
                } else {
                    dftb.aT = dftb.twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    System.out.println("Unable to get the access token.");
                } else {
                    te.printStackTrace();
                }
            }

        }

        dftb.aTID = dftb.twitter.verifyCredentials().getId();
        writeObject(dftb);
//System.exit(0);
    }



    private static void writeObject(DFTwitterBot dftb) throws IOException {
        FileOutputStream fileOut =
                new FileOutputStream("twitterdetails.dftb");
        ObjectOutputStream out =
                new ObjectOutputStream(fileOut);
        System.out.println("Serialising:...");
        System.out.println("Access Token ID: " + dftb.aTID);
        System.out.println("Access Token: " + dftb.aT.toString());
        System.out.println("Tweitter: " + dftb.twitter.toString());
        out.writeObject(dftb);
        out.close();
        fileOut.close();

    }



    public Twitter getTwitter() {
        return twitter;
    }



    public AccessToken getaT() {
        return aT;
    }



    public long getaTID() {
        return aTID;
    }
}
