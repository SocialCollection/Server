package org.socialcollection.connectors.instagram;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.socialcollection.util.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Instagram {
    //insta_user
    //insta_pass
    public static void main(String[] args) throws IOException {
        Instagram4j instagram = Instagram4j.builder().username(Config.readConfig("data", "secrets", "insta_user")).password(Config.readConfig("data", "secrets", "insta_pass")).build();
        instagram.setup();
        instagram.login();

//        System.out.println(getFollower(instagram, "loschjohannes"));
//        System.out.println(getBio(instagram, "loschjohannes"));
//        System.out.println(getCategory(instagram, "loschjohannes"));
//        System.out.println(getStreetAddress(instagram, "loschjohannes"));
//        System.out.println(getExternalURL(instagram, "loschjohannes"));
//        System.out.println(getFeedHashtag(instagram, "github"));

    }
    public static int getFollower(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        return userResult.getUser().getFollower_count();
    }
    public static String getBio(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        return userResult.getUser().getBiography();
    }
    public static String getCategory(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        return userResult.getUser().getCategory();
    }
    public static String getStreetAddress(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        return userResult.getUser().getAddress_street();
    }
    public static String getExternalURL(Instagram4j instagram, String userName) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
        return userResult.getUser().getExternal_url();
    }
    public static String getFeedHashtag(Instagram4j instagram, String hashTag) throws IOException {
        InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest(hashTag));
        List<String> feed = new ArrayList<String>();
        for (InstagramFeedItem feedResult : tagFeed.getItems()) {
            feed.add("https://www.instagram.com/p/" + feedResult.getCode());
        }
        if(!feed.isEmpty()){
            return String.valueOf(feed).replace("[", "").replace("]", "").replace(", ", "\n");
        }
        return null;
    }

}
