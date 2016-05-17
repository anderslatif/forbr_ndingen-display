package model; /**
 * Created by Anders on 5/15/2016.
 */
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
// http://twitter4j.org/en/index.html#howToUse
// http://twitter4j.org/javadoc/twitter4j/Twitter.html

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterAPI {

    // detailed how to http://www.androidhive.info/2012/09/android-twitter-oauth-connect-tutorial/


    // https://apps.twitter.com/app/12363464/keys
    private final static String CONSUMER_KEY = "wMxTD8ghBtALgwAXhoJaMAla1";
    private final static String CONSUMER_KEY_SECRET = "RmPT04DXfrHsYsf2dsxkfhjlSuLNTWriMZbJFUSmGtG1yjg7C3";
    private final static String ACCESS_TOKEN = "731208072534867973-Y20P87WnetWGFIyTTHTyJBWY4kE0v5C";
    private final static String ACCESS_TOKEN_SECRET = "flnC0x6wY3gRf36xE0MEf3p4SrtopugVml6o7zeyvkHcn";


    // forbrændingens twitter side https://twitter.com/search?q=forbraendingen&src=typd
    public static List<String> searchTwitter(String query){
        //Twitter Conf.
        ConfigurationBuilder cB = new ConfigurationBuilder();
        cB.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tF = new TwitterFactory(cB.build());
        Twitter twitter = tF.getInstance();
        Query twitterQuery = new Query("lang:da " + query);
        try{
            //QueryResult result = twitter.search(twitterQuery);  returns List<Status>
            return twitter.search(twitterQuery)
                    .getTweets().stream()
                    // map to text
                    .map(s -> s.getText())
                    //.peek(System.out::println)
                    .collect(Collectors.toList());

        }catch(TwitterException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Status> searchTwitterForStatus(String query){
        //Twitter Conf.
        ConfigurationBuilder cB = new ConfigurationBuilder();
        cB.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tF = new TwitterFactory(cB.build());
        Twitter twitter = tF.getInstance();
        Query twitterQuery = new Query("lang:da " + query);
        try{
            QueryResult result = twitter.search(twitterQuery);

            return result.getTweets();

        }catch(TwitterException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Status> getMentionsTimeline(){
        ConfigurationBuilder cB = new ConfigurationBuilder();
        cB.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tF = new TwitterFactory(cB.build());
        Twitter twitter = tF.getInstance();

        List<Status> statuses = new ArrayList<>();

        try {
            User user = twitter.verifyCredentials();
            System.out.println("Showing @" + user.getScreenName() + "'s mentions.");
            statuses = twitter.getMentionsTimeline();
        } catch(TwitterException e){
            e.printStackTrace();
        }
        return statuses;
    }


    public static List<Status> getRetweetsOfMe(){
        //Twitter Conf.
        ConfigurationBuilder cB = new ConfigurationBuilder();
        cB.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tF = new TwitterFactory(cB.build());
        Twitter twitter = tF.getInstance();

        List<Status> statuses = new ArrayList<>();

        try{
            statuses = twitter.getRetweetsOfMe();
        }catch(TwitterException e){
            throw new RuntimeException(e);
        }
        return statuses;
    }


    public static String getProfileImageURL(long TwitterId){
        ConfigurationBuilder cB = new ConfigurationBuilder();
        cB.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tF = new TwitterFactory(cB.build());
        Twitter twitter = tF.getInstance();

        String url = "";

        try {
            User user = twitter.showUser(TwitterId);
            url = user.getProfileImageURL();

        } catch(TwitterException e){
            e.printStackTrace();
        }
        return url;
    }







}
