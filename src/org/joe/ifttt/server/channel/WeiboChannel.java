package org.joe.ifttt.server.channel;
/**
 * File: 			WeiboChannel.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition and implement of Weibo Channel
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.type.ChannelType;
import org.joe.ifttt.server.user.ChannelUser;
import org.joe.ifttt.server.user.CommonUser;

import com.sina.weibo4j.*;
import com.sina.weibo4j.http.AccessToken;
import com.sina.weibo4j.http.RequestToken;

public class WeiboChannel extends Channel{
	/**Weibo Channel Class, implement some weibo functions*/
	private String textString = "\0";
	private String picString = "\0";
	private static final String ACCESS_SECRET = "access_secret";
	private static final String ACCESS_TOKEN = "access_token";
	private static boolean HAVE_ACCESS_TOKEN = false;
	private ChannelUser user;
	
	public WeiboChannel() {
		/**Default Constructor*/
		super.setChannelType(ChannelType.WEIBO);
		this.user = new ChannelUser();
	}
	
	public WeiboChannel(ChannelUser user) {
		super.setChannelType(ChannelType.WEIBO);
		this.user = new ChannelUser();
		this.user.setUsername(user.getUsername());
		this.user.setPassword(user.getPassword());
	}
	
	public void setUser(ChannelUser user) {
		/**set the user of the weibo Channel*/
		this.user = new ChannelUser();
		this.user.setUsername(user.getUsername());
		this.user.setPassword(user.getPassword());
	}
	public ChannelUser getUser() {
		return this.user;
	}
	public void write(CommonContent content) {
		/**input the content*/
		if (content.getTextString() != null) {
			textString = content.getTextString();
		}
		if (content.getPicString() != null) {
			picString = content.getPicString();
		}
	}
	
	public CommonContent read() {
		/**read the content*/
		CommonContent obj = new CommonContent();
		return obj;
	}
	
	public Status getStatusByName(String weiboUserScreenName) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		Weibo weibo = new Weibo();
		AccessToken accessToken = getToken(weibo);
		User weibouser = null;
		try {
			String screen_name="李开复";
			weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
			weibouser = weibo.showUser(weiboUserScreenName);
			System.out.println(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return weibouser.getStatus();
	}
	
	public java.util.List<Status> getStatusById(String weiboUserId) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		Weibo weibo = new Weibo();
		AccessToken accessToken = getToken(weibo);
		weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
		return getStatus(weiboUserId, weibo);
	}
	
	private java.util.List<Status> getStatus(String id, Weibo weibo) {
		java.util.List<Status> statuses = null;
		Paging pag = new Paging();
		pag.setSinceId(3343021761165196l);
		pag.setCount(200);
		
		//weibouserID ? string? 
		try {
			statuses = weibo.getUserTimeline(id, pag);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +status.getId()+":"+
                               status.getText() + status.getOriginal_pic());
		}
		return statuses;
	}
	
	public void update(CommonContent content) {
		/**update the status with content*/
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		Weibo weibo = new Weibo();
		AccessToken accessToken = getToken(weibo); 
		try {
			weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
			String message = content.getTextString();
			weibo.updateStatus(message);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private AccessToken getToken(Weibo weibo) {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		
		AccessToken accessToken = null;
		try {
			RequestToken requestToken = weibo.getOAuthRequestToken();
			System.out.println("Got request token.");
			System.out.println("Request token: "+ requestToken.getToken());
			System.out.println("Request token secret: "+ requestToken.getTokenSecret());
			
			while (null == accessToken) {
				System.out.println("Open the following URL and grant access to your account:");
				String urlString = requestToken.getAuthorizationURL();
				System.out.println(requestToken.getAuthorizationURL());
				String pinString = getPin(urlString,requestToken.getToken());
				
				String pin = pinString;
				System.out.println("***" + pin);
				try{
					accessToken = requestToken.getAccessToken(pin);
				} catch (WeiboException te) {
					if(401 == te.getStatusCode()){
						System.out.println("Unable to get the access token.");
					}else{
						te.printStackTrace();
					}
				}
			}
			System.out.println("Got access token.");
			System.out.println("Access token: "+ accessToken.getToken());
			System.out.println("Access token secret: "+ accessToken.getTokenSecret());
			System.out.println(accessToken.getToken()+" "+accessToken.getTokenSecret());
		} catch (WeiboException te) {
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit( -1);
		} catch (IOException ioe) {
			System.out.println("Failed to read the system input.");
			System.exit( -1);
		}
		return accessToken;
	}
	
	private String getPin(String url, String token) throws IOException {
		/**get the pin string*/
        String html = readContentByPost(url, token);
        //get the matcher of pin
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("<span class=\"fb\">" +
        		"[^0-9]*[0-9]+</span>").matcher(html);
        boolean flag = matcher.find();
        String pinString = null;
        if (flag) {
            java.lang.String s1 = matcher.group();
            System.out.println("s1:" + s1);
            matcher = java.util.regex.Pattern.compile("[0-9]{6}").matcher(s1);
            //get the pin, length is 6 
            if (matcher.find()) {
                pinString = matcher.group();
            }
        }
        return pinString;
    }
	
	private String readContentByPost(String url, String token) throws IOException {
		/**read the content of the web page by post*/
        URL postUrl = new URL(url);
        // open the connection
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        // set output to connection?
        // this is a post request, args put in text of http, set true
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        // Post cache is false
        connection.setUseCaches(false);

        connection.setInstanceFollowRedirects(true);
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		// content of http
        String content = "userId=" + user.getUsername() + "&passwd=" + user.getPassword()
                         + "&oauth_callback=none" + "&action=submit" + "&from=" + "null"
                         + "&oauth_token=" + token;
        out.writeBytes(content);

        out.flush();
        out.close(); // flush and close
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection
            .getInputStream()));
        String line = "";
        String str = "";
        System.out.println("Contents of post request");
        while ((line = reader.readLine()) != null) {
            str += line;
        }
        System.out.println("Contents of post request ends");
        reader.close();
        connection.disconnect(); //disconnect the connection
        return str;
    }
}

