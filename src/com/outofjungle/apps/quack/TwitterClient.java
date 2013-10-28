package com.outofjungle.apps.quack;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "EN377OjwmEydS0qVV7cTiA";       // Change this
    public static final String REST_CONSUMER_SECRET = "LK1Q4ft5fzR4vU2eKdI8eJq49Ak33USb66ZhFYB9Lw"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://quack"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeLine(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("/statuses/home_timeline.json");
    	client.get(url,  null, handler);
    }

    public void getMentions(JsonHttpResponseHandler handler) {
		String url = getApiUrl("/statuses/mentions_timeline.json");
		client.get(url,  null, handler);
	}
    
    public void getUserTimeline(JsonHttpResponseHandler handler) {
    	String url = getApiUrl("/statuses/user_timeline.json");
    	client.get(url,  null, handler);
    }
    
    public void getMyInfo(JsonHttpResponseHandler handler) {
    	String url = getApiUrl("/account/verify_credentials.json");
    	client.get(url,  null, handler);
    }
    
    
    public void postTweet(String tweet, AsyncHttpResponseHandler handler) {
    	
    	RequestParams params = new RequestParams();
    	params.put("status", tweet);	
    	
    	String url = getApiUrl("/statuses/update.json");
    	client.post(url, params, handler);
    }
}