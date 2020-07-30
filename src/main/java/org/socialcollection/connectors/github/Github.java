package org.socialcollection.connectors.github;

import org.kohsuke.github.GitHub;
import org.socialcollection.util.Config;
import org.socialcollection.util.Logger;
import org.socialcollection.util.WriteFile;

import java.io.IOException;

public class Github {

    public static void main(String[] args) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));

        //returns coding lang
        //System.out.println(gh.getMyself().getAllRepositories().get("personalWebsite").getLanguage());
        //returns MIT LICENSE
        //System.out.println(gh.getMyself().getAllRepositories().get("re.call").getLicense().getName());
        //returns johanneslosch/re.call
        //System.out.println(gh.getMyself().getAllRepositories().get("re.call").getFullName());
        //creates repo, output
        //gh.createRepository("test").description("test description").issues(true).private_(false).create();
        //gh.getMyself().getAllRepositories().get("test").createIssue("first issue").body("body message").label("bug").assignee(gh.getUser("johanneslosch")).create();
        //gh.getMyself().getAllRepositories().get("test").setPrivate(false);;
        //System.out.println(gh.getRepository("johanneslosch/re.call").getLanguage());

        if(!gh.isCredentialValid()){
            System.exit(1);
            Logger.error("Invalid credentials");
        }
        if(gh.isOffline()){
            System.out.println("offline");
        }
        String repo = "firstcontributions/first-contributions";
        if(gh.getRepository(repo).hasIssues()){
            System.out.println(
            gh.getRepository(repo).getIssue(26602).getBody());
        }
        //System.out.println(gh.getRepository("firstcontributions/first-contributions").hasIssues());
        //System.out.println(gh.getRepository("firstcontributions/first-contributions").getForksCount());
        //System.out.println(Arrays.toString(gh.getUser("singhashish18").getKeys().toArray()));
        //followers(gh);
        //System.out.println(gh.getRepository("johanneslosch/re.call").getCompare("dev", "master").getDiffUrl().toString());
    }
    public String getLicenseBody(GitHub gh, String licenseName) throws IOException {
        return gh.getLicense(licenseName).getBody();
    }
    public static void followers(GitHub gh) throws IOException {
        writeFile(gh.getMyself().getFollowers().byLogin("singhashish18").getFollowers().toString());

    }
    public static void writeFile(String content){
        WriteFile.writer("data", "followers", "txt", content);
    }

}
