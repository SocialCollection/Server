package org.socialcollection.connectors.github;

import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;
import org.socialcollection.util.Config;

import java.io.IOException;
import java.net.URL;

public class Github {
    public static void main(String[] args) {
        try {
            System.out.println(getLicense("MIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getIssue(String repo, int issue) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));
        if (gh.getRepository(repo).hasIssues()) {
            if(!gh.getRepository(repo).getIssues(GHIssueState.OPEN).get(issue).isPullRequest()){
                return "ISSUE: " + gh.getRepository(repo).getIssue(gh.getRepository(repo).getIssues(GHIssueState.OPEN).get(issue).getNumber()).getUrl().toString();
            }else{
                return "PR: " + gh.getRepository(repo).getIssue(gh.getRepository(repo).getIssues(GHIssueState.OPEN).get(issue).getNumber()).getUrl().toString();
            }
        }
        return null;
    }
    public static URL getUserURL(String user) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));
        return gh.getUser(user).getHtmlUrl();
    }
    public static String getLicense(String licenseName) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));
        return getLicenseName(licenseName) + " \n" + getLicenseBody(licenseName) + "\n URL: " + getLicenseURL(licenseName);
    }
    private static String getLicenseBody(String licenseName) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));

        return gh.getLicense(licenseName).getBody();
    }
    private static String getLicenseURL(String licenseName) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));

        return String.valueOf(gh.getLicense(licenseName).getUrl());
    }
    private static String getLicenseName(String licenseName) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));

        return gh.getLicense(licenseName).getName();
    }
    public static String getFollowers(String user) throws IOException {
        GitHub gh = GitHub.connect(Config.readConfig("data", "secrets", "GHlogin"), Config.readConfig("data", "secrets", "GHoauth"));
        return gh.getMyself().getFollowers().byLogin(user).getFollowers().toString();

    }
}
