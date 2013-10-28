package com.outofjungle.apps.quack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.outofjungle.apps.quack.fragments.ProfileFragment;
import com.outofjungle.apps.quack.fragments.TimelineFragment;

public class ProfileActivity extends FragmentActivity {

	private ProfileFragment profileFragment;
	private TimelineFragment timelineFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		profileFragment = new ProfileFragment();
		timelineFragment = new TimelineFragment();
		
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		fts.replace(R.id.frame_profile, profileFragment);
		fts.replace(R.id.frame_timeline, timelineFragment);
		fts.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
}
