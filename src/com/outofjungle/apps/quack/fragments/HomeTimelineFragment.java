package com.outofjungle.apps.quack.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.outofjungle.apps.quack.QuackApp;
import com.outofjungle.apps.quack.TwitterStorage;
import com.outofjungle.apps.quack.models.Tweet;

public class HomeTimelineFragment extends TweetsListFragment {
	
	private ArrayList<Tweet> tweets;
	private TwitterStorage datasource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		datasource = new TwitterStorage(getActivity());
		datasource.open();
		refreshTimeline();
	}
	
	private void refreshTimeline() {

		if ( isNetworkAvailable() ) {
			QuackApp.getRestClient().getHomeTimeLine(new JsonHttpResponseHandler(){
	
				@Override
				public void onSuccess(JSONArray jsonTweets) {
					tweets = Tweet.fromJson(jsonTweets);

					getAdapter().clear();
					getAdapter().addAll(tweets);
					

					datasource.clear();
					datasource.save(tweets);
				}
				
				@Override
				public void onFailure(Throwable e, JSONObject error) {
					Log.d("DEBUG", "failed to fetch home timeline");
				}
			});
		} else {
			tweets = datasource.fetch();
			Log.d("DEBUG", String.format("No network. Fetched %s items from database...", tweets.size()));
			
			getAdapter().clear();
			getAdapter().addAll(tweets);
			
		}
	}
}
