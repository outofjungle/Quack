package com.outofjungle.apps.quack;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.outofjungle.apps.quack.models.Tweet;

public class TimelineActivity extends Activity {

	ListView lvTweets;
	ArrayList<Tweet> tweets;
	TweetsAdapter adapter;
	ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupViews();
		
		tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getBaseContext(), tweets);
		
		refreshTimeline();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	private void setupViews() {
		 lvTweets = (ListView) findViewById(R.id.lvTweets);
	}
	
	public void onComposeAction(MenuItem item) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivity(i);
	}

	private void refreshTimeline() {
		dialog = ProgressDialog.show(TimelineActivity.this, "", "updating...");

		QuackApp.getRestClient().getHomeTimeLine(new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONArray jsonTweets) {
				tweets = Tweet.fromJson(jsonTweets);
				adapter.clear();
				adapter.addAll(tweets);
				lvTweets.setAdapter(adapter);
				dialog.dismiss();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject error) {
				dialog.dismiss();
			}
		});
	}
	
	public void onRefreshAction(MenuItem item) {
		refreshTimeline();
	}
}
