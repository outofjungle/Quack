package com.outofjungle.apps.quack.fragments;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.outofjungle.apps.quack.QuackApp;
import com.outofjungle.apps.quack.R;
import com.outofjungle.apps.quack.models.User;

public class ProfileFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_profile, parent, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		loadProfileInfo();
	}
	
	private void loadProfileInfo() {
		
		QuackApp.getRestClient().getMyInfo(
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						User me = User.fromJson(json);
						getActivity().getActionBar().setTitle("@" + me.getScreenName());
						populateProfileHeader(me);
					}
				});
	}

	protected void populateProfileHeader(User user) {
		ImageView ivProfileImage = (ImageView) getActivity().findViewById(R.id.ivProfileImage);
		TextView tvName = (TextView) getActivity().findViewById(R.id.tvName);
		TextView tvTagLine = (TextView) getActivity().findViewById(R.id.tvTagLine);
		TextView tvFollowers = (TextView) getActivity().findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) getActivity().findViewById(R.id.tvFollowing);
		
		tvName.setText(user.getName());
		tvFollowers.setText(user.getFollowersCount() + " Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		tvTagLine.setText(user.getTagLine());
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
	}

	
}
