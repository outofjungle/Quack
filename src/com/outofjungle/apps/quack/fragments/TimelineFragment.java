package com.outofjungle.apps.quack.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.outofjungle.apps.quack.QuackApp;
import com.outofjungle.apps.quack.models.Tweet;

public class TimelineFragment extends TweetsListFragment {

	private ArrayList<Tweet> tweets;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		refreshUserTimeline();
	}
	
	private void refreshUserTimeline() {

		if ( isNetworkAvailable() ) {
			QuackApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler(){
	
				@Override
				public void onSuccess(JSONArray jsonTweets) {
					tweets = Tweet.fromJson(jsonTweets);

					getAdapter().clear();
					getAdapter().addAll(tweets);
				}
				
				@Override
				public void onFailure(Throwable e, JSONObject error) {
					Log.d("DEBUG", "failed to fetch user timeline");
				}
			});
		}	
	}
	
}
